# n8n local

Template pra rodar o n8n na sua máquina, do jeito mais simples possível:
sem banco de dados externo (usa SQLite por baixo dos panos) e sem
HTTPS/domínio — é só pra uso local mesmo. Inclui também o suporte a Code
node em Python (veja a seção abaixo).

Por padrão sobe a versão `latest` (estável mais recente) do n8n. Também é possível ligar, à
parte, um serviço extra com o modo **Agents** em beta (veja a seção
[Modo Agents (beta, opcional)](#modo-agents-beta-opcional)).

## Pré-requisitos

- [Docker](https://docs.docker.com/get-docker/) e Docker Compose instalados
  (no Docker Desktop os dois já vêm juntos).

## Como subir

1. Entre nesta pasta (`local/`).
2. Copie o arquivo de variáveis de ambiente:
   ```bash
   cp .env.example .env
   ```
   Se quiser, ajuste o `GENERIC_TIMEZONE` no `.env`. As variáveis
   `N8N_INSTANCE_AI_MODEL*` só precisam ser preenchidas se você for usar o
   serviço beta opcional (veja a seção
   [Modo Agents (beta, opcional)](#modo-agents-beta-opcional)).
3. Suba o n8n:
   ```bash
   docker compose up -d
   ```
4. Acesse **http://localhost:5678** no navegador. Na primeira vez, o próprio
   n8n vai pedir pra você criar um usuário (owner) — isso é o cadastro
   padrão do n8n, não precisa configurar nada extra.

## Modo Agents (beta, opcional)

O serviço padrão (`n8n`) roda a versão **`latest`** e não tem o modo
Agents habilitado. Se você quiser experimentar o modo **Agents** / AI
Assistant do n8n (beta, disponível a partir da versão 2.32.3), existe um
segundo serviço opcional, `n8n-agents-beta`, rodando a versão 2.33.6 com
`N8N_ENABLED_MODULES=instance-ai,agents` — ele fica num
[profile](https://docs.docker.com/compose/how-tos/profiles/) separado
(`beta`) e não sobe com o `docker compose up -d` normal, então os dois
podem conviver sem conflito.

1. Preencha no `.env`:
   ```dotenv
   N8N_INSTANCE_AI_MODEL=openai/gpt-5.5
   N8N_INSTANCE_AI_MODEL_API_KEY=sua-chave-do-provedor
   ```
   Também é possível usar um modelo Anthropic ou OpenRouter, conforme os
   exemplos comentados no `.env.example`.
2. Suba o serviço beta (o profile também sobe o `task-runner-beta` junto):
   ```bash
   docker compose --profile beta up -d
   ```
3. Acesse **http://localhost:5680** — é uma instância separada da padrão
   (dados, workflows e credenciais próprios, no volume `n8n_beta_data`),
   então o que você criar lá não aparece em `http://localhost:5678`.

Pra desligar só o serviço beta, mantendo o padrão no ar:

```bash
docker compose --profile beta down
```

O modo Agents funciona no modo de execução regular usado por este template;
ele ainda não deve ser combinado com queue mode. Recursos adicionais do
Assistant/Agents que executam código isolado ou usam base de conhecimento
pedem um sandbox separado. Ele não foi incluído neste template mínimo porque
o sandbox local oficial usa Docker-in-Docker privilegiado e exige pelo menos
4 GB de RAM e 2 CPUs. Os agentes básicos e o node **AI Agent** continuam
disponíveis sem esse conjunto extra de containers.

## Comandos úteis

| O que eu quero | Comando |
| --- | --- |
| Ver logs | `docker compose logs -f` |
| Atualizar pra `latest` mais recente | `docker compose pull && docker compose up -d` |
| Parar (mantendo os dados) | `docker compose stop` |
| Subir de novo | `docker compose start` |
| Parar e remover o container | `docker compose down` |
| **Apagar tudo, inclusive workflows salvos** | `docker compose down -v` |

Seus workflows e credenciais ficam guardados no volume Docker `n8n_data` —
eles sobrevivem a `docker compose down` (sem `-v`) e a reinicializações da
máquina.

O `docker compose up -d` não baixa uma `latest` nova sozinho se já existir
uma imagem local — use o `pull` acima pra atualizar. Pra fixar uma versão
(n8n e task runner juntos), defina `N8N_VERSION` no `.env`, ex.:
`N8N_VERSION=2.40.5`.

Esses comandos afetam só o serviço padrão. O serviço beta (perfil `beta`,
se você o ativou) precisa do `--profile beta` na frente pra ser incluído,
por exemplo `docker compose --profile beta down -v` — que apaga também o
volume `n8n_beta_data`.

## Code node em Python

O template sobe dois containers: `n8n` e `task-runner`. O `task-runner` é
quem executa o código quando você usa o Code node em modo **Python** — a
imagem principal do n8n não traz Python 3 embutido, então sem esse segundo
container você vê o erro `Python runner unavailable: Python 3 is missing
from this system`. Não precisa fazer nada extra: os dois já sobem juntos
com `docker compose up -d`. O serviço beta tem seu próprio par
(`n8n-agents-beta` e `task-runner-beta`), que sobe junto quando você usa
`--profile beta`.

## Trabalhando com arquivos locais

A pasta `./local-files` (criada automaticamente na primeira subida) fica
acessível dentro do n8n em `/files`. Use esse caminho em nodes como
"Read/Write Files from Disk" para ler ou gravar arquivos que você também
consegue ver direto no seu computador, nessa pasta.

## Não incluído de propósito

Esse template não tem autenticação extra, HTTPS ou banco Postgres — é só
pra rodar localmente. Pra colocar o n8n acessível na internet, use o
template `../provisioning/`.
