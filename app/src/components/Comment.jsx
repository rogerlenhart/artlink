import { formatarDataHora } from '../helper/utils'

export function Comment({ comentario }) {
  const { autor } = comentario

  return (
    <div className="flex gap-4 w-full">
      <img className="rounded-full w-12 h-12 object-cover" src={autor.imagemPerfil} alt={autor.nome} />
      <div className="flex flex-col gap-2 rounded bg-zinc-700 p-3 flex-1">
        <div className="flex flex-col">
          <span className="text-lg font-semibold">{autor.nome}</span>
          <span className="text-sm text-zinc-400">@{autor.apelido}</span>
        </div>

        <p>{comentario.mensagem}</p>

        <span className="text-zinc-400">{formatarDataHora(comentario.dataCriacao)}</span>
      </div>
    </div>
  )
}
