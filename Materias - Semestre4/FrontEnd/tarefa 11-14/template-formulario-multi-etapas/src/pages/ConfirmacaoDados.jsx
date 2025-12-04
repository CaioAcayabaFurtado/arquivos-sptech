import { Button } from "../components/Button";
import { CardInfo } from "../components/CardInfo";
import { Indicador } from "../components/Indicador";
import { useNavigate } from 'react-router-dom'
import { useForm } from '../provider/FormContext'

export function ConfirmacaoDados() {
  const navigate = useNavigate()
  const { data } = useForm()

  function handleBack() {
    navigate('/user-address')
  }

  function handleFinish() {
    navigate('/done')
  }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen  from-blue-50 via-white to-blue-100 px-4">
      <div className="w-full max-w-lg bg-white shadow-2xl rounded-3xl p-8 border border-blue-100">

        <h2 className="text-3xl font-semibold text-center mb-4 text-blue-900">Confirme seus dados</h2>

        <Indicador steps={4} stepAtual={3} />

        <div className="space-y-4 mb-8">
          <CardInfo label="Nome" descricao={data.nome || '---'} />
          <CardInfo label="E-mail" descricao={data.email || '---'} />
          <CardInfo label="Telefone" descricao={data.telefone || '---'} />
          <CardInfo label="Endereço" descricao={data.endereco || '---'} />
          <CardInfo label="Cidade" descricao={data.cidade || '---'} />
          <CardInfo label="Estado" descricao={data.estado || '---'} />
        </div>

        <div className="flex justify-between gap-4">
          <Button type="button" onClick={handleBack}>Voltar</Button>
          <Button type="button" onClick={handleFinish}>Finalizar</Button>
        </div>
      </div>
    </div>
  );
}
