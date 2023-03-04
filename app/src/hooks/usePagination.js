import { useState } from 'react'

const PAGINA_MINIMA = 0

export function usePagination() {
  const [page, setPage] = useState(PAGINA_MINIMA)

  function handlePreviousPage() {
    if (page <= PAGINA_MINIMA) return;

    setPage(page => page - 1)
  }

  function handleNextPage(pageLimit) {
    if (page >= pageLimit) return;

    setPage(page => page + 1)
  }

  function handleGoToPage(page){
    setPage(page)
  }

  return {
    handlePreviousPage,
    handleNextPage,
    handleGoToPage,
    page
  }
}
