import { useState } from "react";

export function Pilha() {
  const [pilha, setPilha] = useState(Array.from({ length: 10 }, () => null));
  const [valor, setValor] = useState("");
  const [topo, setTopo] = useState(-1);
  const [mensagem, setMensagem] = useState("");

  // Verifica se a pilha está cheia
  function isFull() {
    return topo === pilha.length - 1;
  }

  // Verifica se a pilha está vazia
  function isEmpty() {
    return topo === -1;
  }

  // Insere um item no topo da pilha
  function push() {
    if (valor.trim() === "") {
      setMensagem("Informe um valor para inserir.");
      return;
    }
    if (isFull()) {
      setMensagem("A pilha está cheia. Não é possível inserir.");
      return;
    }
    // próxima posição é topo + 1
    const novoTopo = topo + 1;
    const novaPilha = [...pilha];
    novaPilha[novoTopo] = valor; 
    setPilha(novaPilha);
    setTopo(novoTopo);
    setMensagem(`Valor '${valor}' inserido no topo (índice ${novoTopo}).`);
    setValor("");
  }

  // Remove o item do topo da pilha
  function pop() {
    if (isEmpty()) {
      setMensagem("A pilha já está vazia. Nada para remover.");
      return;
    }
    const valorRemovido = pilha[topo];
    const novaPilha = [...pilha];
    novaPilha[topo] = null; 
    const novoTopo = topo - 1;
    setPilha(novaPilha);
    setTopo(novoTopo);
    setMensagem(`Valor '${valorRemovido}' removido do topo.`);
  }

  // Mostra o item no topo da pilha
  function peek() {
    if (isEmpty()) {
      setMensagem("A pilha está vazia. Sem elemento no topo.");
      return;
    }
    setMensagem(`Topo (índice ${topo}): '${pilha[topo]}'`);
  }

  return (
    <div className="flex flex-col items-center mt-10 space-y-6">
      <h2 className="text-2xl font-bold text-gray-800">
        Pilha (Stack) - Capacidade: 10
      </h2>

      <div className="flex gap-2">
        <input
          type="text"
          value={valor}
          onChange={(e) => setValor(e.target.value)}
          placeholder="Novo elemento"
          className="border border-gray-400 rounded-lg px-3 py-1 focus:outline-none focus:ring-2 focus:ring-blue-400"
        />
        <button
          onClick={push}
          className="bg-blue-500 hover:bg-blue-600 text-white px-3 py-1 rounded-lg"
        >
          Push
        </button>
        <button
          onClick={pop}
          className="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded-lg"
        >
          Pop
        </button>
        <button
          onClick={peek}
          className="bg-yellow-500 hover:bg-yellow-600 text-white px-3 py-1 rounded-lg"
        >
          Peek
        </button>
      </div>

      {mensagem && (
        <p className="text-sm text-gray-700 font-medium mt-2">{mensagem}</p>
      )}

      <div className="flex flex-col-reverse items-center mt-6">
        {pilha.map((item, i) => (
          <div
            key={i}
            className={`w-32 h-10 flex items-center justify-center rounded-lg border mb-1 ${
              i === topo ? "border-blue-500 bg-blue-100" : "border-gray-300"
            }`}
          >
            {item ?? ""}
          </div>
        ))}
      </div>

      {isEmpty() && (
        <p className="text-gray-500 italic mt-4">A pilha está vazia.</p>
      )}

      {isFull() && (
        <p className="text-gray-500 italic mt-4">A pilha está cheia.</p>
      )}
    </div>
  );
}
