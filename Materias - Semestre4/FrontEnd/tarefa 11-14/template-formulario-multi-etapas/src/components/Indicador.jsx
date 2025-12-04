import React from 'react'

/**
 * @param {{steps:number,stepAtual:number}} props
 */
export function Indicador({ steps, stepAtual }) {
  const largura = (stepAtual / steps) * 100 + '%'

  return (
    <div className="flex items-center mb-6">
      <div className="flex-1 h-2 bg-blue-200 rounded-full mr-2">
        <div
          className="h-2 bg-blue-900 rounded-full transition-all duration-300"
          style={{ width: largura }}
        ></div>
      </div>
      <span className="text-sm text-gray-500">
        Step {stepAtual} de {steps}
      </span>
    </div>
  )
}
