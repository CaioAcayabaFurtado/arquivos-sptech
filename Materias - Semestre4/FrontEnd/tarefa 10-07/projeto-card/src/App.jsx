import Card from "./Card";
import reactLogo from "./assets/react.svg";
import viteLogo from "/public/vite.svg";

const cards = [
  {
    titulo: "React Card",
    imagem: reactLogo,
    descricao: "Este é um card escuro usando React.",
    tipo: "dark",
    desabilitado: false
  },
  {
    titulo: "Vite Card",
    imagem: viteLogo,
    descricao: "Este card está desabilitado.",
    tipo: "dark",
    desabilitado: true
  },
  {
    titulo: "Minimal Card",
    imagem: reactLogo,
    descricao: "Não será exibida.",
    tipo: "minimalist",
    desabilitado: false
  },
  {
    titulo: "Outro Card",
    imagem: viteLogo,
    descricao: "Card minimalista desabilitado.",
    tipo: "minimalist",
    desabilitado: true
  }
];

export default function App() {
  return (
    <div style={{
      minHeight: "100vh",
      background: "#f7f7f7",
      padding: "40px",
      backgroundColor: "#282c34",
    }}>
      <h1>Lista de Cards</h1>
      <div style={{
        display: "flex",
        flexWrap: "wrap",
        gap: "24px"
      }}>
        {cards.map((card, idx) => (
          <Card
            key={idx}
            titulo={card.titulo}
            imagem={card.imagem}
            descricao={card.descricao}
            tipo={card.tipo}
            desabilitado={card.desabilitado}
          />
        ))}
      </div>
    </div>
  );
}