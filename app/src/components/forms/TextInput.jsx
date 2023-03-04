import { FormGroup } from "./FormGroup"

export function TextInput({
  id,
  name,
  type = 'text',
  label,
  value,
  handleChange,
  error,
  placeholder
}) {
  return (
    <FormGroup label={label} id={id} error={error}>

      <input
        className="
          bg-zinc-900 text-base p-3 placeholder:text-zinc-400
          border-2 border-purple-600 border-opacity-0 
          focus:border-opacity-100 transition duration-200
          rounded outline-none w-full
        "
        id={id}
        name={name}
        type={type}
        value={value}
        onChange={handleChange}
        placeholder={placeholder}
      />
    </FormGroup>
  )
}
