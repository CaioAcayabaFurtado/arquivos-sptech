# Atividade: API REST e Servidor MCP — Catálogo de Livros

## Objetivo

Desenvolver um catálogo simples de livros em memória e disponibilizá-lo de duas formas:

1. Uma API REST em Node.js com Express.
2. Um servidor MCP acessível por HTTP, para que um agente de IA no n8n possa cadastrar e consultar livros.

> Os dados devem existir somente enquanto os servidores estiverem em execução. Não utilizar banco de dados; ao reiniciar, o catálogo deve ser apagado.

## Contrato de dados

### Livro retornado pela API

```json
{
  "id": 1,
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "ano": 1899,
  "genero": "Romance"
}
```

### Dados para cadastro

```json
{
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "ano": 1899,
  "genero": "Romance"
}
```

| Campo | Tipo | Regra |
| --- | --- | --- |
| `id` | número inteiro | Gerado pelo servidor, sequencial, a partir de `1`. |
| `titulo` | texto | Obrigatório. |
| `autor` | texto | Obrigatório. |
| `ano` | número inteiro | Opcional. |
| `genero` | texto | Opcional. |

---

## Arquitetura proposta

```text
n8n (AI Agent + MCP Client Tool)
              |
              | HTTP: /mcp
              v
      Servidor MCP (porta 3001)
              |
              | módulo compartilhado
              v
   Catálogo em memória (array JavaScript)
              ^
              | módulo compartilhado
              |
       API REST Express (porta 3000)
```

### Decisão de implementação

Usar um módulo compartilhado para o repositório em memória e para as regras de negócio. Assim, API e MCP manipulam o mesmo catálogo quando executados no mesmo processo, sem duplicar validações ou depender de chamadas HTTP internas.

Estrutura inicial sugerida:

```text
.
├── src/
│   ├── app.js                 # configuração das rotas Express
│   ├── api-server.js          # inicialização da API REST
│   ├── mcp-server.js          # inicialização do servidor MCP HTTP
│   ├── index.js               # inicia API e MCP no mesmo processo
│   ├── livros-repositorio.js  # array em memória e geração de ID
│   ├── livros-service.js      # regras de cadastro e consulta
│   └── livros-schema.js       # validação Zod reutilizável
├── package.json
├── .env.example
└── README.md
```

---

## Plano de desenvolvimento

### 1. Preparar o projeto

- Inicializar o projeto Node.js com `npm init`.
- Instalar `express`, `@modelcontextprotocol/sdk` e `zod`.
- Criar um script que inicie API e MCP no mesmo processo, preservando um único catálogo em memória.
- Definir variáveis `API_PORT=3000` e `MCP_PORT=3001`, com esses valores como padrão.
- Registrar no `.env.example` apenas nomes de variáveis, sem dados sensíveis.

### 2. Criar a camada do catálogo

- Criar um array privado para armazenar os livros.
- Criar um contador iniciado em `1` para geração sequencial de IDs.
- Implementar as operações `cadastrar`, `listar` e `buscarPorId`.
- Centralizar a validação em um schema Zod: `titulo` e `autor` obrigatórios; `ano` e `genero` opcionais.

### 3. Implementar a API REST

- Criar a aplicação Express e habilitar `express.json()`.
- Implementar as rotas abaixo utilizando a camada de catálogo.

| Método | Rota | Resultado de sucesso | Falhas esperadas |
| --- | --- | --- | --- |
| `POST` | `/livros` | `201` e livro criado | `400` se `titulo` ou `autor` forem inválidos/ausentes. |
| `GET` | `/livros` | `200` e array de livros | — |
| `GET` | `/livros/:id` | `200` e livro encontrado | `404` se o ID não existir. |

- Validar o parâmetro `id` como inteiro positivo.
- Padronizar respostas de erro em JSON, por exemplo: `{ "erro": "Livro não encontrado" }`.
- Iniciar o servidor com `app.listen(API_PORT, '0.0.0.0')`.

### 4. Implementar o servidor MCP

- Criar o servidor com o SDK oficial `@modelcontextprotocol/sdk`.
- Expor transporte HTTP em `/mcp` (Streamable HTTP ou SSE), nunca `stdio`.
- Registrar as ferramentas a seguir, reutilizando o mesmo serviço de livros.

| Ferramenta | Entrada | Comportamento |
| --- | --- | --- |
| `cadastrar_livro` | `titulo`, `autor`, `ano?`, `genero?` | Valida os dados e cria o livro. |
| `listar_livros` | nenhuma | Retorna todos os livros cadastrados. |
| `buscar_livro` | `id` inteiro positivo | Retorna o livro correspondente ou informa que não foi encontrado. |

- Escrever descrições objetivas nas ferramentas, pois elas orientam a escolha do agente de IA.
- Transformar os resultados e erros em conteúdo compreensível para o agente.
- Iniciar o servidor MCP em `0.0.0.0`, na porta `MCP_PORT`.

### 5. Integrar e validar no n8n

- Criar um workflow com os nós **AI Agent**, um modelo de chat e **MCP Client Tool**.
- Configurar a URL conforme o ambiente:

| Cenário | URL do MCP |
| --- | --- |
| n8n em container e MCP na máquina local | `http://host.docker.internal:3001/mcp` |
| n8n e MCP em containers na mesma rede Docker | `http://mcp-livros:3001/mcp` |

- Publicar a porta `3001` caso o MCP esteja em container.
- No Linux, se necessário, adicionar `host.docker.internal:host-gateway` ao container do n8n.

---

## Roteiro de testes

### Testes da API REST

```bash
curl -X POST http://localhost:3000/livros \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Dom Casmurro","autor":"Machado de Assis","ano":1899,"genero":"Romance"}'

curl http://localhost:3000/livros
curl http://localhost:3000/livros/1
```

Também validar:

- `POST /livros` sem `titulo` ou `autor` retorna `400`.
- `GET /livros/999` retorna `404`.
- O primeiro livro recebe ID `1` e os próximos IDs são sequenciais.
- Reiniciar o servidor limpa o catálogo.

### Testes do agente no n8n

Usar frases naturais e confirmar que o agente chama a ferramenta apropriada:

- “Cadastre o livro *O Cortiço*, de Aluísio Azevedo, de 1890.”
- “Quais livros estão cadastrados?”
- “Mostre o livro de ID 1.”

---

## Critérios de aceite

- [ ] A API REST possui as três rotas exigidas e usa `express.json()`.
- [ ] IDs são sequenciais, gerados no servidor e iniciam em `1`.
- [ ] `titulo` e `autor` são obrigatórios e retornam `400` quando inválidos.
- [ ] Uma busca por ID inexistente retorna `404`.
- [ ] O MCP expõe as três ferramentas por HTTP em `/mcp`.
- [ ] As entradas das ferramentas MCP são validadas com Zod.
- [ ] API e MCP escutam em `0.0.0.0`.
- [ ] O n8n consegue cadastrar, listar e buscar livros pelo MCP.
- [ ] Os dados permanecem somente em memória.

## Atenção à conectividade

`localhost` e `127.0.0.1` aceitam conexões apenas no próprio ambiente. Para o n8n em outro container alcançar os servidores, eles devem escutar em `0.0.0.0` e as portas necessárias devem estar publicadas.
