import { axiosInstance } from "./_base/axiosInstance";

export const loginQuery = ({username, password}) => (
  axiosInstance.post('/login', {}, {
    auth: {
      username,
      password
    }
  })
)

export const logoutQuery = () => (
  axiosInstance.post('/logout')
)

export const perfilAutenticadoQuery = () => (
  axiosInstance.get(`/perfil`)
)