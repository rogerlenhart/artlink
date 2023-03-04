
export function Label({htmlFor, children}) {
  return (
    <label className='text-lg font-semibold' htmlFor={htmlFor}>
      {children}
    </label>
  )
}
