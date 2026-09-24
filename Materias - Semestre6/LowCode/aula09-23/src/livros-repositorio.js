const livros = [];
let proximoId = 1;

export function salvar(dadosLivro) {
  const livro = { id: proximoId, ...dadosLivro };
  proximoId += 1;
  livros.push(livro);
  return livro;
}

export function listar() {
  return [...livros];
}

export function buscarPorId(id) {
  return livros.find((livro) => livro.id === id) ?? null;
}
