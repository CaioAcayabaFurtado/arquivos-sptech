import { createContext, useContext, useState } from 'react'

const FormContext = createContext(null)

export function FormProvider({ children }) {
  const [data, setData] = useState({
    nome: '',
    email: '',
    telefone: '',
    endereco: '',
    cidade: '',
    estado: '',
  })

  function setPersonal(personal) {
    setData((prev) => ({ ...prev, ...personal }))
  }

  function setAddress(address) {
    setData((prev) => ({ ...prev, ...address }))
  }

  function clear() {
    setData({ nome: '', email: '', telefone: '', endereco: '', cidade: '', estado: '' })
  }

  return (
    <FormContext.Provider value={{ data, setPersonal, setAddress, clear }}>
      {children}
    </FormContext.Provider>
  )
}

export function useForm() {
  const ctx = useContext(FormContext)
  if (!ctx) {
    throw new Error('useForm must be used within a FormProvider')
  }
  return ctx
}

export default FormContext
