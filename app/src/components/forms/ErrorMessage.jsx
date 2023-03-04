
export function ErrorMessage({error}) {
  if (error === '') return

  return (
    <span className='inline-block font-semibold text-lg text-red-500'>
      {error}
    </span>
  )
}
