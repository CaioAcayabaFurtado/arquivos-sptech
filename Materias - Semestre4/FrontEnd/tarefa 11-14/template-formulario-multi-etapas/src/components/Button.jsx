import { twMerge } from 'tailwind-merge'

// NÃO MEXA NESSE ARQUIVO!

/**
 * @param {React.ButtonHTMLAttributes<HTMLInputElement>} props
 */

export function Button({ children, className, ...props }) {
  return (
    <button
      {...props}
      className={twMerge(
        `
        inline-flex
        items-center
        justify-center
        rounded-md
        bg-blue-900
        px-4
        py-2
        text-sm
        font-medium
        text-white
        shadow-sm
        transition-all
        duration-200
        hover:bg-blue-950
        focus:outline-none
        focus:ring-2
        focus:ring-blue-500
        focus:ring-offset-2
        active:scale-[0.98]
        disabled:opacity-60
        disabled:cursor-not-allowed
        `,
        className
      )}
    >
      {children}
    </button>
  )
}
