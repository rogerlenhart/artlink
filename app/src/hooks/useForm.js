import { useState } from 'react'

import { VALIDATIONS } from '../helper/validations'

export function useForm({
  defaultValues,
  validationRules,
  onSubmit
}) {
  const [formData, setFormData] = useState(defaultValues)

  function handleInputChange(event) {
    const target = event.target
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;
    
    setFormData(oldFormData => ({...oldFormData, [name]: {...oldFormData[name], value: value}}))
  }

  function handleSubmit(event) {
    event.preventDefault()

    const isValid = validate()

    if(isValid){
      onSubmit(formData)
    }
  }

  function validate(){
    const fieldsWithErrors = Object.entries(formData).map(([name, field]) => {
      const value = field.value

      const failedValidation = validationRules[name].find(validationRule => {
        return !VALIDATIONS[validationRule].validate(value)
      })

      const error = failedValidation ? VALIDATIONS[failedValidation].message : ''

      return [name, { value, error }]
    })

    const newFormData = Object.fromEntries(fieldsWithErrors)
    
    setFormData(newFormData)

    const hasErrors = fieldsWithErrors.some(([, field]) => field.error !== '')

    return !hasErrors
  }

  return {
    formData,
    setFormData,
    handleInputChange,
    handleSubmit
  }
}
