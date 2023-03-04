import { ErrorMessage } from './ErrorMessage'
import { Label } from './Label'

export function FormGroup({ children, label, id, error}) {
  return (
    <div className="flex flex-col gap-3 w-full">
      {label ? <Label htmlFor={id}>{label}</Label> : null}
      {children}
      <ErrorMessage error={error}/>
    </div>
  )
}
