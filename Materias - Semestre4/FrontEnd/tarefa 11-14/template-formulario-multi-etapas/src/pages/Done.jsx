import React from 'react'
import { Button } from '../components/Button'
import { useForm } from '../provider/FormContext'
import { useNavigate } from 'react-router-dom'

export default function Done() {
  const { data, clear } = useForm()
  const navigate = useNavigate()

  function handleRestart() {
    clear()
    navigate('/')
  }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen from-blue-50 via-white to-blue-100 px-4">
      <div className="w-full max-w-lg bg-white shadow-2xl rounded-3xl p-8 border border-blue-100 text-center">
        <h2 className="text-3xl font-semibold mb-4 text-blue-900">Cadastro concluído</h2>
        <p className="mb-6 text-lg">Obrigado, <span className="font-bold">{data.nome || 'Usuário'}</span>! Seus dados foram recebidos.</p>
        <Button onClick={handleRestart}>Voltar ao início</Button>
      </div>
    </div>
  )
}
