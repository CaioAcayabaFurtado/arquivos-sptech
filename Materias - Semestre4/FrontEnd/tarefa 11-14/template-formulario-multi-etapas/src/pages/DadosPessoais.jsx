import { Button } from "../components/Button";
import { Indicador } from "../components/Indicador";
import { Input } from "../components/Input";
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useForm } from '../provider/FormContext'

export function DadosPessoais() {
  const navigate = useNavigate()
  const { data, setPersonal } = useForm()

  const [nome, setNome] = useState(data.nome)
  const [email, setEmail] = useState(data.email)
  const [telefone, setTelefone] = useState(data.telefone)

  function handleSubmit(e) {
    e.preventDefault()
    // campos obrigatórios
    if (!nome || !email || !telefone) {
      alert('Preencha todos os campos')
      return
    }
    setPersonal({ nome, email, telefone })
    navigate('/user-address')
  }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen from-blue-50 via-white to-blue-100 px-4">
      <div className="w-full max-w-lg bg-white shadow-2xl rounded-3xl p-8 border border-blue-100">

        <h2 className="text-3xl font-semibold text-center mb-4 text-blue-900">
          Insira seus dados pessoais
        </h2>

        <Indicador steps={4} stepAtual={1} />

        <form className="space-y-6" onSubmit={handleSubmit}>
          <div className="flex flex-col">
            <label className="text-sm font-medium text-gray-700 mb-1">Nome completo</label>
            <Input placeholder="Digite seu nome" value={nome} onChange={(e) => setNome(e.target.value)} />
          </div>

          <div className="flex flex-col">
            <label className="text-sm font-medium text-gray-700 mb-1">E-mail</label>
            <Input placeholder="seuemail@exemplo.com" type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
          </div>

          <div className="flex flex-col">
            <label className="text-sm font-medium text-gray-700 mb-1">Telefone</label>
            <Input placeholder="(00) 00000-0000" type="tel" value={telefone} onChange={(e) => setTelefone(e.target.value)} />
          </div>

          <div className="w-full flex justify-end">
            <Button type="submit">Proximo</Button>
          </div>
        </form>
      </div>
    </div>
  )
}
