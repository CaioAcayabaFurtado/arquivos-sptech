import React from 'react'

/**
 * @param {{label:string,descricao:string}} props
 */

export function CardInfo(props) {
  return (
     <div className="flex justify-between items-center p-4 bg-blue-50 rounded-xl border border-blue-100">
        <span className="text-gray-600 font-medium">{props?.label}</span>
        <span className="text-blue-900 font-semibold">{props?.descricao}</span>
      </div>
  )
}
