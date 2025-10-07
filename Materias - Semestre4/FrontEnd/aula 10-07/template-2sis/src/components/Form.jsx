import { useState, useEffect, use } from "react";

export default function Form () {   
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");

    const alterarNome = (e) => {
        setNome(e.target.value);
    }

    const alterarEmail = (e) => {
        setEmail(e.target.value);
    }

    useEffect(() => {
        console.log("O componente foi montado");
    }, []);

    useEffect(() => {
        console.log("O nome foi alterado");
    }, [nome]);

    useEffect(() => {
        console.log("O email foi alterado");
    }, [email]);

    return (
        <>
            <h1>Formulário</h1>
            <label>
                <span>Nome:</span>
                <input className="border-1" type="text" onChange={(e) => alterarNome(e)}/>
            </label>
            <label>
                <span>E-mail:</span>
                <input className="border-1" type="email" onChange={(e) => alterarEmail(e)}/>
            </label>
            <div className="flex gap-1 ">
                <p>{nome}</p>
                <p>{email}</p>
            </div>
        </>
    );
}