import { Button } from "./forms"

export function FormBox({ children, title, buttonText, handleSubmit }) {
  return (
    <div className="flex flex-col items-center gap-6 w-[480px]">
      <h1 className='font-bold text-4xl'>{title}</h1>
      <div className="bg-zinc-800 p-12 w-full">
        <form className='flex flex-col gap-8 w-full' onSubmit={handleSubmit}>
          <div className="flex flex-col gap-2 items-center w-full">
            {children}
          </div>
          <Button full>{buttonText}</Button>
        </form>
      </div>
    </div>
  )
}