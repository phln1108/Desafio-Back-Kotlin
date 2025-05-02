package br.com.stellar.form


import kotlinx.serialization.Serializable
import jakarta.validation.constraints.*
import jakarta.validation.Valid


@Serializable
data class CreateUsuarioForm(

    @field:NotBlank(message = "Informe o nome.")
    val nome: String,

    @field:NotBlank(message = "Informe o email.")
    @field:Email(message = "Informe um email válido.")
    var email: String,

    @field:NotBlank(message = "Informe a senha.")
    @field:Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    var senha: String,

    @field:Valid // Para validação em cascata
    val endereco: CreateEnderecoForm,

    val isAdmin: Boolean = false
)