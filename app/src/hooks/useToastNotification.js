import { toast } from "react-toastify"

export function useToastNotification() {

  const toastSuccess = message => toast.success(message)

  const toastError = error => toast.error(getErrorMessage(error))
  const getErrorMessage = error => error.response?.data?.message ?? error.message

  return {
    toastError,
    toastSuccess
  }
}
