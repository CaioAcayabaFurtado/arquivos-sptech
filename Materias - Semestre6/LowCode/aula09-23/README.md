# Catálogo de Livros

API REST e servidor MCP para cadastro e consulta de livros em memória.

## Requisitos

- Node.js 18 ou superior
- npm

## Instalação

```bash
npm install
```

## Execução

```bash
npm start
```

Por padrão, os servidores ficam disponíveis em:

- API REST: `http://localhost:3000`
- MCP: `http://localhost:3001/mcp`

As portas podem ser alteradas pelas variáveis `API_PORT` e `MCP_PORT`:

```bash
API_PORT=3000 MCP_PORT=3001 npm start
```

Os dados são armazenados somente em memória e são perdidos quando o processo é encerrado.

## API REST

### Cadastrar livro

```bash
curl -X POST http://localhost:3000/livros \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Dom Casmurro","autor":"Machado de Assis","ano":1899,"genero":"Romance"}'
```

### Listar livros

```bash
curl http://localhost:3000/livros
```

### Buscar livro

```bash
curl http://localhost:3000/livros/1
```

`titulo` e `autor` são obrigatórios. `ano` deve ser um número inteiro e `genero` é opcional.

## Servidor MCP no n8n

No n8n, adicione um **MCP Client Tool** ao workflow do agente e configure o endpoint:

- n8n em container e MCP na máquina local: `http://host.docker.internal:3001/mcp`
- n8n e MCP na mesma rede Docker: `http://mcp-livros:3001/mcp`

O workflow deve conter um **AI Agent**, um modelo de chat e o **MCP Client Tool**. As ferramentas disponíveis são:

- `cadastrar_livro`
- `listar_livros`
- `buscar_livro`

### Importar workflow pronto

O arquivo `workflow-n8n-catalogo-livros.json` pode ser importado em **Import from File** no n8n. Depois da importação:

1. Abra o nó **OpenAI Chat Model** e selecione ou crie uma credencial OpenAI.
2. Confirme que o nó **MCP Catálogo de Livros** usa `http://localhost:3001/mcp`.
3. Se o n8n estiver em Docker, altere o endpoint para `http://host.docker.internal:3001/mcp`.
4. Salve o workflow e execute o **Chat Trigger**.

Em um ambiente Docker, publique a porta `3001` do servidor MCP. No Linux, pode ser necessário mapear `host.docker.internal` para `host-gateway` no container do n8n.

## Testes

```bash
npm test
```

A suíte verifica as rotas REST, validações, IDs sequenciais e as três ferramentas MCP usando Streamable HTTP.
