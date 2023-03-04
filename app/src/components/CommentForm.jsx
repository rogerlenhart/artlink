import React from 'react'
import { formatarDataHora } from '../helper/utils'
import { Button, TextInput } from './forms'

export function CommentForm({ usuario, handleSubmit, handleChange, value }) {

  const disabled = value == ""

  return (
    <form className="flex gap-4 w-full" onSubmit={handleSubmit}>
      <img className="rounded-full w-12 h-12 object-cover" src={usuario.imagemPerfil} alt={usuario.nome} />
      <div className='flex flex-col w-full gap-4'>
        <textarea
          className='rounded outline-none border-2 border-purple-600 border-opacity-0 focus:border-opacity-100 bg-zinc-900 p-3 flex-1 placeholder:text-zinc-400 resize-none'
          id="mensagem"
          name="mensagem"
          placeholder="Escreva um comentário..."
          value={value}
          onChange={handleChange}
        />
        <Button disabled={disabled}>PUBLICAR</Button>
      </div>
    </form>
  )
}
