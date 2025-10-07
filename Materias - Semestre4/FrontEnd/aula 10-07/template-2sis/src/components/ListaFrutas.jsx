import { useState, useEffect } from "react";
export default function ListaFrutas() {

    const [frutas, setFrutas] = useState([]);

    async function buscarFrutas() {
        const resposta = await fetch("http://localhost:3000/frutas");
        const dados = await resposta.json();
        if (dados) {
            setFrutas(dados); 
        }
        console.log(dados);
    }

    async function removerFruta(fruta) {
        const copiaFrutas = [...frutas];
        const index = copiaFrutas.indexOf(fruta);
        if (index > -1) {
            copiaFrutas.splice(index, 1);
            setFrutas(copiaFrutas);
        }
        buscarFrutas();
    }

    useEffect(() => {  
        buscarFrutas();
    }, []);

    return (
        <ul>
            {frutas.map((fruta, index) => (
                <li key={index}>{fruta}</li>
            ))}
        </ul>
    );
}