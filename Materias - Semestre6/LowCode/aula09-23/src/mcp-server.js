import express from 'express';
import { McpServer } from '@modelcontextprotocol/sdk/server/mcp.js';
import { StreamableHTTPServerTransport } from '@modelcontextprotocol/sdk/server/streamableHttp.js';
import { ZodError, z } from 'zod';
import { buscarLivro, cadastrarLivro, listarLivros } from './livros-service.js';
import { idSchema, livroSchema } from './livros-schema.js';

const buscaSchema = z.object({ id: idSchema });

function criarServidorMcp() {
  const servidor = new McpServer({
    name: 'catalogo-de-livros',
    version: '1.0.0'
  });

  servidor.registerTool(
    'cadastrar_livro',
    {
      description: 'Cadastra um livro no catálogo. Use quando o usuário pedir para adicionar ou registrar um livro.',
      inputSchema: livroSchema
    },
    async (dados) => {
      try {
        const livro = cadastrarLivro(dados);
        return respostaJson(livro);
      } catch (erro) {
        return respostaDeErro(erro);
      }
    }
  );

  servidor.registerTool(
    'listar_livros',
    {
      description: 'Lista todos os livros atualmente cadastrados no catálogo. Use quando o usuário pedir a relação de livros.',
      inputSchema: z.object({})
    },
    async () => respostaJson(listarLivros())
  );

  servidor.registerTool(
    'buscar_livro',
    {
      description: 'Busca um livro pelo seu identificador numérico. Use quando o usuário informar ou solicitar um ID específico.',
      inputSchema: buscaSchema
    },
    async ({ id }) => {
      try {
        const livro = buscarLivro(id);
        if (!livro) {
          return {
            content: [{ type: 'text', text: 'Livro não encontrado.' }],
            isError: true
          };
        }
        return respostaJson(livro);
      } catch (erro) {
        return respostaDeErro(erro);
      }
    }
  );

  return servidor;
}

function respostaJson(dados) {
  return {
    content: [{ type: 'text', text: JSON.stringify(dados) }],
    structuredContent: Array.isArray(dados) ? { livros: dados } : { livro: dados }
  };
}

function respostaDeErro(erro) {
  const mensagem = erro instanceof ZodError
    ? erro.issues.map((issue) => issue.message).join('; ')
    : 'Não foi possível concluir a operação.';

  return { content: [{ type: 'text', text: mensagem }], isError: true };
}

export function iniciarMcp(porta) {
  const app = express();
  app.use(express.json());

  app.post('/mcp', async (req, res) => {
    const servidor = criarServidorMcp();
    const transporte = new StreamableHTTPServerTransport({ sessionIdGenerator: undefined });

    res.on('close', () => transporte.close());

    try {
      await servidor.connect(transporte);
      await transporte.handleRequest(req, res, req.body);
    } catch (erro) {
      console.error('Erro no servidor MCP:', erro);
      if (!res.headersSent) {
        res.status(500).json({ erro: 'Erro interno do servidor MCP' });
      }
    }
  });

  app.all('/mcp', (_req, res) => {
    res.status(405).json({ erro: 'Método não permitido. Use POST.' });
  });

  return new Promise((resolve, reject) => {
    const servidor = app.listen(porta, '0.0.0.0', () => {
      console.log(`Servidor MCP rodando em http://0.0.0.0:${porta}/mcp`);
      resolve(servidor);
    });
    servidor.once('error', reject);
  });
}
