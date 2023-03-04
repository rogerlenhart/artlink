export function formatarDataHora(data) {
  const objetoData = new Date(data)
  return objetoData.toLocaleString()
}

export function formatarData(data) {
  const objetoData = new Date(data)
  return objetoData.toLocaleDateString()
}