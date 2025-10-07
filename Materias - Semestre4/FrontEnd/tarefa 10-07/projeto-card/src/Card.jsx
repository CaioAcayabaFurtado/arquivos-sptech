import "./Card.css";

export default function Card({ titulo, imagem, descricao, tipo, desabilitado }) {
  const isDark = tipo === "dark";
  const isMinimalist = tipo === "minimalist";

  return (
    <div className={`card ${isDark ? "card-dark" : ""} ${desabilitado ? "card-disabled" : ""}`}>
      <img src={imagem} alt={titulo} className="card-img" />
      {!isMinimalist && (
        <>
          <h2 className="card-title">{titulo}</h2>
          <p className="card-desc">{descricao}</p>
        </>
      )}
      <button className="card-btn" disabled={desabilitado}>
        {desabilitado ? "Desabilitado" : "Acessar"}
      </button>
    </div>
  );
}