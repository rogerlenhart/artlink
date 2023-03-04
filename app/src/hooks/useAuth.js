
import { loginQuery, logoutQuery, perfilAutenticadoQuery } from "../api/perfil"
import useGlobalUser from "../context/user"
import { useMutation } from "./useMutation"

export function useAuth() {
  const [, setUser] = useGlobalUser()

  const {mutate: updateUser} = useMutation({
    query: perfilAutenticadoQuery,
    onSuccess: (response => setUser(response))
  })

  const {mutate: login} = useMutation({
    query: loginQuery,
    onSuccess: (response) => updateUser()
  })

  const {mutate: logout} = useMutation({
    query: logoutQuery,
    onSuccess: (response) => setUser(null)
  })

  return {login, logout, updateUser}
}
