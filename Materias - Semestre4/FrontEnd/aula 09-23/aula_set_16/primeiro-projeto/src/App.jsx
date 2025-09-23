import Titulo from "./components/Titulo";

// por padrão, exporta a função App para quem importar o arquivo App.jsx
export default function App() {
  const valor = "Primeira mensagem"; // ao carregar o componente, cria essa constante (mas não é observada pelo React!)

  return (
    <>
      <Titulo mensagem={valor} /> {/* as chaves indicam o uso de comandos JS, neste caso, referencia a variável 'valor' */}

      <Titulo mensagem="Olá, mundo!" /> {/* sem as chaves, estamos passando um valor de texto estático */}
    </>
  );
}
