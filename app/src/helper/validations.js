export const VALIDATIONS = {
  required: {
    validate: (value) => value !== '' && value !== [],
    message: 'Este campo é obrigatório.'
  }
}