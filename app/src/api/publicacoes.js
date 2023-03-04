import { axiosInstance } from "./_base/axiosInstance";

export const listarPublicacoesQuery = ({page = 0}) => (
  axiosInstance.get(`/publicacoes?page=${page}&sort=dataCriacao,desc&size=15`)
)

export const detalharPublicacaoQuery = ({id}) => (
  axiosInstance.get(`/publicacoes/${id}`)
)

export const curtirPublicacaoQuery = ({id}) => (
  axiosInstance.post(`/publicacoes/${id}/curtidas`)
)

export const removerCurtidaPublicacaoQuery = ({id}) => (
  axiosInstance.delete(`/publicacoes/${id}/curtidas`)
)

export const comentarPublicacaoQuery = ({id, mensagem}) => (
  axiosInstance.post(`/publicacoes/${id}/comentarios`, {
    mensagem
  })
)