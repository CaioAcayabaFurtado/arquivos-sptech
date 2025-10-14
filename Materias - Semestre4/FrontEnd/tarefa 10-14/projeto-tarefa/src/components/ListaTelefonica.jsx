import { useState } from "react";

function ListaTelefonica() {
  const [nome, setNome] = useState("");
  const [telefone, setTelefone] = useState("");
  const [contatos, setContatos] = useState([]);

  function cadastrar() {
    if (nome && telefone) {
      setContatos([...contatos, { nome, telefone }]);
      setNome("");
      setTelefone("");
    }
  }

  function excluir(index) {
    setContatos(contatos.filter((_, i) => i !== index));
  }

  return (
    <div>
      <h2>ListaTelefonica</h2>
      <input value={nome} onChange={e => setNome(e.target.value)} placeholder="Nome" />
      <input value={telefone} onChange={e => setTelefone(e.target.value)} placeholder="Telefone" />
      <button onClick={cadastrar}>Cadastrar</button>
      <ul>
        {contatos.map((c, i) => (
          <li key={i}>
            {c.nome} - {c.telefone}
            <button onClick={() => excluir(i)}>Excluir</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ListaTelefonica;
