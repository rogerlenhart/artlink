import { Link } from "react-router-dom"

export function Post({ publicacao }) {
  const { autor } = publicacao

  return (
    <Link to={`/posts/${publicacao.id}`} key={publicacao.id} className="relative w-full min-h-[200px] group">
      <img className="aspect-square object-cover" src={publicacao.imagem} alt={publicacao.titulo} />
      <div className="flex items-end h-full w-full absolute left-0 bottom-0 p-2 opacity-0 group-hover:opacity-100 transition bg-gradient-to-b from-transparent to-black/50">
        <div className="flex gap-2 w-full">
          <img className="rounded-full w-12 h-12 object-cover" src={autor.imagemPerfil} alt={autor.nome} />
          <div className="flex flex-col">
            <span className="text-lg font-semibold">
              {publicacao.titulo}
            </span>
            <span className="text-sm">
              {autor.nome}
            </span>
          </div>
        </div>
      </div>
    </Link>
  )
}
