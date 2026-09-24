import { criarApp } from './app.js';

export function iniciarApi(porta) {
  const app = criarApp();
  return new Promise((resolve, reject) => {
    const servidor = app.listen(porta, '0.0.0.0', () => {
      console.log(`API REST rodando em http://0.0.0.0:${porta}`);
      resolve(servidor);
    });
    servidor.once('error', reject);
  });
}
