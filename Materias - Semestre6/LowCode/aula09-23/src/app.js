import express from 'express';
import { ZodError } from 'zod';
import { buscarLivro, cadastrarLivro, listarLivros } from './livros-service.js';

export function criarApp() {
  const app = express();
  app.use(express.json());

  app.post('/livros', (req, res) => {
    try {
      const livro = cadastrarLivro(req.body);
      return res.status(201).json(livro);
    } catch (erro) {
      return responderErroDeValidacao(res, erro);
    }
  });

  app.get('/livros', (_req, res) => res.status(200).json(listarLivros()));

  app.get('/livros/:id', (req, res) => {
    try {
      const livro = buscarLivro(req.params.id);
      if (!livro) {
        return res.status(404).json({ erro: 'Livro não encontrado' });
      }
      return res.status(200).json(livro);
    } catch (erro) {
      return responderErroDeValidacao(res, erro);
    }
  });

  return app;
}

function responderErroDeValidacao(res, erro) {
  if (erro instanceof ZodError) {
    return res.status(400).json({
      erro: 'Dados inválidos',
      detalhes: erro.issues.map((issue) => issue.message)
    });
  }

  console.error(erro);
  return res.status(500).json({ erro: 'Erro interno do servidor' });
}
