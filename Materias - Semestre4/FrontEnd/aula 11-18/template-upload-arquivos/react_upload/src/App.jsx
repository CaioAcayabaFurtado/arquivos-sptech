import { useEffect, useState } from "react";
import axios from "axios";

export default function App() {

  const [arquivo, setArquivo] = useState(null);
  const [arquivos, setArquivos] = useState([]);
  const [preview, setPreveiw] = useState(null);

  async function buscarArquivos() {
    // Lógica para buscar arquivos do servidor
    const response = await axios.get("http://localhost:3000/arquivos")
    console.log(response.data);
    setArquivos(response.data.arquivos);
  }

  useEffect(() => {
    buscarArquivos();
  }, []);

  async function atualizarArquivo(event) {
    const arquivo = event.target.files[0];
    setArquivo(arquivo);
  }

  async function enviar() {
    const formData = new FormData();
    formData.append("arquivo", arquivo);
    console.log(arquivo);
    const response = await fetch("http://localhost:3000/upload", {
      method: "POST",
      body: formData
    })

    if (!response.ok) {
      console.error("Erro ao enviar o arquivo");
    } else {
      const data = await response.json();
      alert(data.mensagem);
      buscarArquivos();
      console.log("Arquivo enviado com sucesso", data);
    }
  }

  async function mostrarImagem(nomeArquivo) {
    setPreveiw(`http://localhost:3000/arquivo/${nomeArquivo}`)
  }


  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center p-6">
      <div className="bg-white shadow-xl rounded-2xl p-8 w-full max-w-2xl">
        <h1 className="text-3xl font-bold text-gray-800 mb-6 text-center">
          Upload de Arquivos
        </h1>

        {/* Upload */}
        <div className="flex flex-col items-center gap-4 mb-8">
          <input
            onChange={atualizarArquivo}
            type="file"

            className="block w-full text-sm text-gray-600
              file:mr-4 file:py-2 file:px-4
              file:rounded-full file:border-0
              file:text-sm file:font-semibold
              file:bg-blue-600 file:text-white
              hover:file:bg-blue-700
              cursor-pointer
            "
          />

          <button
            onClick={enviar}
            className="bg-green-600 hover:bg-green-700 text-white py-2 px-6 rounded-lg font-semibold shadow-md transition">
            Enviar Arquivo
          </button>
        </div>

        <hr className="my-6" />

        {/* Lista de arquivos */}
        <h2 className="text-xl font-semibold text-gray-700 mb-4">
          Arquivos no Servidor
        </h2>


        {arquivos.length === 0 ?
          (<p className="text-gray-500">Nenhum arquivo encontrado.</p>) : (

            <ul className="space-y-3">
              {arquivos.map((arquivo, index) => (
                <li key={index} className="flex justify-between items-center bg-gray-50 p-3 rounded-lg border border-gray-200">
                  <span className="text-gray-700">{arquivo}</span>

                  <button onClick={() => mostrarImagem(arquivo)} className="bg-blue-600 hover:bg-blue-700 text-white py-1 px-4 rounded-lg text-sm font-medium shadow-sm transition">
                    Ver
                  </button>
                </li>
              ))}
            </ul>
            )}

      {/* Preview */}
      {preview && ( 
      <div className="mt-8 text-center">
        <h3 className="text-lg font-semibold text-gray-700 mb-4">
          Preview da Imagem
        </h3>
        <img
          alt="preview"
          className="w-64 mx-auto rounded-lg shadow-md border border-gray-300"
          src={preview}
        />
      </div>
      )}
    </div>
    </div >
  );
}
