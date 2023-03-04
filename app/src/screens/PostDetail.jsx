import { useParams } from "react-router-dom";
import { Comment, CommentForm, Loader } from "../components";
import { Button } from "../components/forms";
import { Section } from "../components/layout/basic";
import { useForm, usePost } from "../hooks";

import heart from "../assets/heart.svg"
import heartFull from "../assets/heart-full.svg"
import forum from "../assets/forum.svg"
import useGlobalUser from "../context/user";

const defaultValues = {
  mensagem: { value: '', error: '' }
}

const validationRules = {
  mensagem: ['required']
}

export function PostDetail() {
  const { id } = useParams()

  const [user] = useGlobalUser()

  const { post, curtirPost, removerCurtidaPost, comentarPost } = usePost(id)

  const { formData, handleInputChange, handleSubmit, setFormData } = useForm({
    defaultValues,
    onSubmit: handleComentar,
    validationRules: validationRules
  })

  function handleComentar() {
    const mensagem = formData.mensagem.value
    comentarPost({ mensagem }, {
      onSuccess: (response) => setFormData({ mensagem: { value: "", error: "" } })
    })
  }

  if (post.loading) return <Loader />

  const data = post.data

  function renderComentarios() {
    return data.comentarios.map(comentario => (
      <Comment key={comentario.id} comentario={comentario} />
    ))
  }

  return (
    <Section containerClassName="max-w-none p-0">
      <article className="w-full h-full flex">
        <div className="h-full flex-1 p-10 flex justify-center items-center">
          <img className="w-full" src={data.imagem} alt={data.titulo} />
        </div>
        <div className="max-h-[calc(100vh_-_80px)] max-w-md h-full bg-zinc-800 p-8 overflow-y-auto">
          <header className="mb-10">
            <div className="flex">
              <div className="flex gap-2 flex-1">
                <img className="rounded-full w-12 h-12 object-cover" src={data.autor.imagemPerfil} alt="" />
                <div className="flex flex-col">
                  <span className="font-semibold text-xl">{data.autor.nome}</span>
                  <span className="text-sm">@{data.autor.apelido}</span>
                </div>
              </div>
              <Button>ADICIONAR</Button>
            </div>
          </header>
          <section className="flex flex-col gap-4 mb-6">
            <h1 className="text-3xl">{data.titulo}</h1>
            <p className="text-zinc-400">{data.descricao}</p>
          </section>
          <section className="flex gap-6">
            <span className="flex gap-2">
              <img src={data.foiCurtido ? heartFull : heart} alt="Likes" onClick={data.foiCurtido ? removerCurtidaPost : curtirPost} className="cursor-pointer" />
              <span className="text-xl font-semibold">{data.curtidas}</span>
            </span>
            <span className="flex gap-2">
              <img src={forum} alt="Comentários" />
              <span className="text-xl font-semibold">{data.numeroComentarios}</span>
            </span>
          </section>
          <hr className="my-4" />
          <section className="flex flex-col gap-6">
            <div className="mb-2">
              <span className="text-zinc-400 text-xl">{data.numeroComentarios} Comentários</span>
            </div>
            <CommentForm usuario={user} handleSubmit={handleSubmit} value={formData.mensagem.value} handleChange={handleInputChange} />
            {renderComentarios()}
          </section>
        </div>
      </article>
    </Section>
  )
}
