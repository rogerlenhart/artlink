import { comentarPublicacaoQuery, curtirPublicacaoQuery, detalharPublicacaoQuery, removerCurtidaPublicacaoQuery } from '../api/publicacoes'
import { useMutation } from './useMutation'
import { useQuery } from './useQuery'

export function usePost(id) {
  const [post, refetchPost] = useQuery(() => detalharPublicacaoQuery({id}))

  const {mutate: curtirPost} = useMutation({
    query: () => curtirPublicacaoQuery({id}),
    onSuccess: (response) => refetchPost()
  })

  const {mutate: removerCurtidaPost} = useMutation({
    query: () => removerCurtidaPublicacaoQuery({id}),
    onSuccess: (response) => refetchPost()
  })

  const {mutate: comentarPost} = useMutation({
    query: ({mensagem}) => comentarPublicacaoQuery({id, mensagem}),
    onSuccess: (response) => refetchPost()
  })

  return { post, curtirPost, removerCurtidaPost, comentarPost }
}
