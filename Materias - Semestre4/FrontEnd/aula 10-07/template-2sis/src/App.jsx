import { useState, useEffect } from "react";
import Form from "./components/Form";
import ListaFrutas from "./components/ListaFrutas";
export default function App () {
  const [contador, setContador] = useState(0);

  const Incrementar = () => {
    setContador(contador + 1);
  }

  const Decrementar = () => {
    setContador(contador - 1);
  }

  return (
    <>
      {/* <h1 className="bg-red-500">Componente App.jsx</h1>
      <p>{contador}</p>
      <button className="cursor-pointer border-1" onClick={() => Incrementar()}>Incrementar</button>
      <button className="cursor-pointer border-1" onClick={() => Decrementar()}>Decrementar</button> */}
      <Form />
      <ListaFrutas />
    </>
  )
}