// a função Titulo recebe o parâmetro 'props', o que indica que o componente utilizará propriedades dinâmicas

import { useState } from "react";

export default function Titulo(props) {
  const [count, setContador] = useState(0);
  return (
    <>
      <h1> {props.mensagem} </h1>
      <p>Votos: {count}</p>
      <button onClick={() => setContador((count) => count + 1)}>Contar</button>
    </>
  );
}

/* 
   'props' representa o objeto com todas as propriedades e atributos recebidos pelo componente 
   'props.mensagem' acessa a informação 'mensagem' que o componente recebeu

   A mensagem será 'undefined' caso não seja passada na instância do componente, como:

      <Titulo mensagem="Teste" />    - recebe a props.mensagem com valor "Teste"
      <Titulo />    - não recebe props.mensagem (fica como undefined)
      <Titulo mensagem="Teste1" exemplo="Teste2" />   - recebe 2 parâmetros, props.exemplo e props.mensagem
*/
