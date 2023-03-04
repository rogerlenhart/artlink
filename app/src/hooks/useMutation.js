import { useState } from "react"
import {useToastNotification} from "./useToastNotification"

export function useMutation({ query, onSuccess }) {
  const { toastError } = useToastNotification()
  const [data, setData] = useState(null)
  const [loading, setLoading] = useState(false)

  async function mutate(params, config) {

    try {
      setData(null)
      setLoading(true)

      const { data } = await query(params)

      setData(data)

      if (onSuccess) {
        await onSuccess(data)
      }

      if (config?.onSuccess) {
        config.onSuccess(data)
      }

    } catch (error) {
      if (config?.onError) {
        config.onError(error)
      } else {
        toastError(error)
      }
    } finally {
      setLoading(false)
    }
  }

  return { mutate, data, loading }
}
