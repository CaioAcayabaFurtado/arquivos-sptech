// import './App.css';
import Calculadora from './components/Calculadora';
import ListaTelefonica from './components/ListaTelefonica';
import Contador from './components/Contador';
import ExibirPersonagens from './components/ExibirPersonagens';

function App() {
  return (
    <div style={{ padding: '20px', maxWidth: '900px', margin: 'auto' }}>
      <Calculadora />
      <hr />
      <ListaTelefonica />
      <hr />
      <Contador />
      <hr />
      <ExibirPersonagens />
    </div>
  );
}

export default App
