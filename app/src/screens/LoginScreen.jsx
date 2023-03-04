import { useEffect, useState } from "react";

import { FormBox } from "../components";
import { ErrorMessage, TextInput } from "../components/forms";

import { useNavigate } from "react-router-dom";
import useGlobalUser from "../context/user";

import { useAuth, useForm } from "../hooks";
import { useToastNotification } from "../hooks/useToastNotification";

const defaultValues = {
  email: { value: '', error: '' },
  password: { value: '', error: '' },
}

const validationRules = {
  email: ['required'],
  password: ['required'],
}

export function LoginScreen() {
  const { toastError } = useToastNotification()
  const navigate = useNavigate()
  const [user] = useGlobalUser()
  const { login } = useAuth()

  const [loginError, setLoginError] = useState("");

  const { formData, handleInputChange, handleSubmit } = useForm({
    defaultValues,
    onSubmit: loginOnSubmit,
    validationRules: validationRules
  })

  async function loginOnSubmit() {
    const username = formData.email.value
    const password = formData.password.value
    login({ username, password }, {
      onError: (error) => {
        if (error.response.status === 401) {
          setLoginError("Usuário ou senha incorretos.")
        } else {
          toastError(error)
        }
      }
    })
  }

  useEffect(() => {
    if (user) {
      navigate('/')
    }
  }, [user]) // eslint-disable-line

  return (
    <FormBox title="LOGIN" buttonText="ENTRAR" handleSubmit={handleSubmit}>
      <ErrorMessage error={loginError} />
      <TextInput
        id="email" name="email"
        placeholder="E-mail" label="E-mail"
        handleChange={handleInputChange}
        value={formData.email.value}
        error={formData.email.error}
      />
      <TextInput
        id="password" name="password"
        placeholder="Senha" label="Senha"
        handleChange={handleInputChange}
        value={formData.password.value}
        error={formData.password.error}
        type="password"
      />
    </FormBox>
  )
}
