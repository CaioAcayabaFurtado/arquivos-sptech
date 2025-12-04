import React from 'react'
import { twMerge } from 'tailwind-merge'

// NÃO MEXA NESSE ARQUIVO!

/**
 * @param {React.InputHTMLAttributes<HTMLInputElement>} props
 */
export function Input(props) {
  return (
    <input
      type={props.type}
      {...props}
      className={twMerge(`
        w-full
        rounded-md
        border
        border-gray-300
        bg-white
        px-3
        py-2
        text-gray-900
        shadow-sm
        transition-all
        duration-200
        focus:border-blue-950
        focus:ring-2
        focus:ring-blue-900
        focus:outline-none
        placeholder:text-gray-400
        disabled:bg-gray-100
        disabled:text-gray-500
        disabled:cursor-not-allowed 
      `, props.className)}
    />
  )
}


