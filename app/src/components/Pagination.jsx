import React from 'react'
import { Button } from './forms'

const PAGINA_MINIMA = 0

export function Pagination({ page, totalPages, handlePreviousPage, handleNextPage, handleGoToPage }) {
  const disablePreviousButton = page === PAGINA_MINIMA
  const disableNextButton = page + 1 === totalPages

  return (
    <div className="flex flex-wrap items-center justify-center gap-6 text-3xl text-center max-sm:flex-col">
      <Button handleClick={() => handleGoToPage(PAGINA_MINIMA)} disabled={disablePreviousButton}>Primeira</Button>
      <div className='flex items-center gap-6'>
        <Button handleClick={handlePreviousPage} disabled={disablePreviousButton}>❮</Button>
        <p>{`${page + 1} de ${totalPages}`}</p>
        <Button handleClick={() => handleNextPage(totalPages)} disabled={disableNextButton}>❯</Button>
      </div>
      <Button handleClick={() => handleGoToPage(totalPages - 1)} disabled={disableNextButton}>Última</Button>
    </div>
  )
}
