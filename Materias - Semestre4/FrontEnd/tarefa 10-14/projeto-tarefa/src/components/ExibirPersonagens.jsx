import { useState, useEffect } from "react";

function ExibirPersonagens() {
  const [personagens, setPersonagens] = useState([]);

  useEffect(() => {
    fetch("https://rickandmortyapi.com/api/character")
      .then(res => res.json())
      .then(data => setPersonagens(data.results || []));
  }, []);

  return (
    <div>
      <h2>ExibirPersonagens</h2>
      <div style={{ display: "flex", flexWrap: "wrap", gap: "10px" }}>
        {personagens.map(p => (
          <div key={p.id} style={{ border: "1px solid #ccc", padding: "10px", width: "150px" }}>
            <img src={p.image} alt={p.name} style={{ width: "100%" }} />
            <div>{p.name}</div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ExibirPersonagens;
