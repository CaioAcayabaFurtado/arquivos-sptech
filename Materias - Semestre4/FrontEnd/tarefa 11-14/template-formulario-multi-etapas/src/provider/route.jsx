import React from 'react'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { FormProvider } from './FormContext'
import { DadosPessoais } from '../pages/DadosPessoais'
import { DadosEndereco } from '../pages/DadosEndereco'
import { ConfirmacaoDados } from '../pages/ConfirmacaoDados'
import Done from '../pages/Done'

export default function RoutesProvider() {
	return (
		<BrowserRouter>
			<FormProvider>
				<Routes>
					<Route path="/" element={<DadosPessoais />} />
					<Route path="/user-address" element={<DadosEndereco />} />
					<Route path="/confirm-data" element={<ConfirmacaoDados />} />
					<Route path="/done" element={<Done />} />
					<Route path="*" element={<Navigate to="/" replace />} />
				</Routes>
			</FormProvider>
		</BrowserRouter>
	)
}