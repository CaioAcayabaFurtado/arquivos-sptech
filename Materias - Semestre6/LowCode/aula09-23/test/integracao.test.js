import assert from 'node:assert/strict';
import { after, before, test } from 'node:test';
import { Client } from '@modelcontextprotocol/sdk/client/index.js';
import { StreamableHTTPClientTransport } from '@modelcontextprotocol/sdk/client/streamableHttp.js';
import { iniciarApi } from '../src/api-server.js';
import { iniciarMcp } from '../src/mcp-server.js';

let api;
let mcp;
let apiUrl;
let mcpUrl;

before(async () => {
  [api, mcp] = await Promise.all([iniciarApi(0), iniciarMcp(0)]);
  apiUrl = `http://127.0.0.1:${api.address().port}`;
  mcpUrl = `http://127.0.0.1:${mcp.address().port}/mcp`;
});

after(async () => {
  await Promise.all([fecharServidor(api), fecharServidor(mcp)]);
});

test('API cadastra, lista, busca e valida livros', async () => {
  const cadastro = await fetch(`${apiUrl}/livros`, {
    method: 'POST',
    headers: { 'content-type': 'application/json' },
    body: JSON.stringify({ titulo: 'Dom Casmurro', autor: 'Machado de Assis', ano: 1899 })
  });

  assert.equal(cadastro.status, 201);
  assert.deepEqual(await cadastro.json(), {
    id: 1,
    titulo: 'Dom Casmurro',
    autor: 'Machado de Assis',
    ano: 1899
  });

  const segundoCadastro = await fetch(`${apiUrl}/livros`, {
    method: 'POST',
    headers: { 'content-type': 'application/json' },
    body: JSON.stringify({ titulo: 'O Cortiço', autor: 'Aluísio Azevedo' })
  });
  assert.equal(segundoCadastro.status, 201);
  assert.equal((await segundoCadastro.json()).id, 2);

  const lista = await fetch(`${apiUrl}/livros`);
  assert.equal(lista.status, 200);
  assert.equal((await lista.json()).length, 2);

  const inexistente = await fetch(`${apiUrl}/livros/999`);
  assert.equal(inexistente.status, 404);

  const idInvalido = await fetch(`${apiUrl}/livros/abc`);
  assert.equal(idInvalido.status, 400);

  const invalido = await fetch(`${apiUrl}/livros`, {
    method: 'POST',
    headers: { 'content-type': 'application/json' },
    body: JSON.stringify({ titulo: 'Livro sem autor' })
  });
  assert.equal(invalido.status, 400);

  const tituloVazio = await fetch(`${apiUrl}/livros`, {
    method: 'POST',
    headers: { 'content-type': 'application/json' },
    body: JSON.stringify({ titulo: '   ', autor: 'Autor' })
  });
  assert.equal(tituloVazio.status, 400);

  const anoDecimal = await fetch(`${apiUrl}/livros`, {
    method: 'POST',
    headers: { 'content-type': 'application/json' },
    body: JSON.stringify({ titulo: 'Livro', autor: 'Autor', ano: 2024.5 })
  });
  assert.equal(anoDecimal.status, 400);
});

test('MCP cadastra, lista e busca usando Streamable HTTP', async () => {
  const cliente = new Client({ name: 'teste-catalogo', version: '1.0.0' });
  await cliente.connect(new StreamableHTTPClientTransport(new URL(mcpUrl)));

  const cadastro = await cliente.callTool({
    name: 'cadastrar_livro',
    arguments: { titulo: 'O Cortiço', autor: 'Aluísio Azevedo', ano: 1890, genero: 'Romance' }
  });
  assert.equal(cadastro.isError, undefined);
  assert.match(cadastro.content[0].text, /O Cortiço/);

  const lista = await cliente.callTool({ name: 'listar_livros', arguments: {} });
  assert.match(lista.content[0].text, /Dom Casmurro/);
  assert.match(lista.content[0].text, /O Cortiço/);
  assert.deepEqual(Object.keys(lista.structuredContent), ['livros']);

  const busca = await cliente.callTool({ name: 'buscar_livro', arguments: { id: 2 } });
  assert.match(busca.content[0].text, /O Cortiço/);

  const livroInexistente = await cliente.callTool({ name: 'buscar_livro', arguments: { id: 999 } });
  assert.equal(livroInexistente.isError, true);
  assert.match(livroInexistente.content[0].text, /não encontrado/i);

  const idInvalido = await cliente.callTool({ name: 'buscar_livro', arguments: { id: 0 } });
  assert.equal(idInvalido.isError, true);

  await cliente.close();
});

function fecharServidor(servidor) {
  return new Promise((resolve, reject) => {
    servidor.close((erro) => (erro ? reject(erro) : resolve()));
  });
}
