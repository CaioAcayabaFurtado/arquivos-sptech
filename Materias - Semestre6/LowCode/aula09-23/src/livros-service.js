import { idSchema, livroSchema } from './livros-schema.js';
import * as repositorio from './livros-repositorio.js';

export function cadastrarLivro(dados) {
  const livroValidado = livroSchema.parse(dados);
  return repositorio.salvar(livroValidado);
}

export function listarLivros() {
  return repositorio.listar();
}

export function buscarLivro(id) {
  const idValidado = idSchema.parse(id);
  return repositorio.buscarPorId(idValidado);
}
