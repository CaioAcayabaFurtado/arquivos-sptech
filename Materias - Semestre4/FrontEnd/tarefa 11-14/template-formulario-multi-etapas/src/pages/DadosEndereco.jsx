import React, { useState } from 'react'
import { Input } from '../components/Input'
import { Button } from '../components/Button'
import { Indicador } from '../components/Indicador'
import { useNavigate } from 'react-router-dom'
import { useForm } from '../provider/FormContext'

export function DadosEndereco() {
  const navigate = useNavigate()
  const { data, setAddress } = useForm()

  const [endereco, setEndereco] = useState(data.endereco)
  const [cidade, setCidade] = useState(data.cidade)
  const [estado, setEstado] = useState(data.estado)

  function handleBack() {
    navigate(-1)
  }

  function handleSubmit(e) {
    e.preventDefault()
    if (!endereco || !cidade || !estado) {
      alert('Preencha todos os campos do endereço')
      return
    }
    setAddress({ endereco, cidade, estado })
    navigate('/confirm-data')
  }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen from-blue-50 via-white to-blue-100 px-4">
      <div className="w-full max-w-lg bg-white shadow-2xl rounded-3xl p-8 border border-blue-100">

        <h2 className="text-3xl font-semibold text-center mb-4 text-blue-900">Insira seus dados de endereço</h2>

        <Indicador steps={4} stepAtual={2} />

        <form className="space-y-6" onSubmit={handleSubmit}>
          <div className="flex flex-col">
            <label className="text-sm font-medium text-gray-700 mb-1">Endereço</label>
            <Input placeholder="Digite seu endereço" value={endereco} onChange={(e) => setEndereco(e.target.value)} />
          </div>

          <div className="flex flex-col">
            <label className="text-sm font-medium text-gray-700 mb-1">Cidade</label>
            <Input placeholder="Digite sua cidade" type="text" value={cidade} onChange={(e) => setCidade(e.target.value)} />
          </div>

          <div className="flex flex-col">
            <label className="text-sm font-medium text-gray-700 mb-1">Estado</label>
            <Input placeholder="Digite seu estado" value={estado} onChange={(e) => setEstado(e.target.value)} />
          </div>
          <div className="w-full flex justify-between">
            <Button type="button" onClick={handleBack}>Voltar</Button>
            <Button type="submit">Proximo</Button>
          </div>
        </form>
      </div>
    </div>
  )
}
