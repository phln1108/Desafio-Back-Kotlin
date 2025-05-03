package br.com.stellar.form

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import kotlinx.serialization.Serializable

@Serializable
data class LoginForm(
    @field:NotBlank(message = "Informe o email.")
    @field:Email(message = "Informe um email válido.")
    var email: String,

    @field:NotBlank(message = "Informe a senha.")
    @field:Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    var senha: String,
)
