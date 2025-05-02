package br.com.stellar.form

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import kotlinx.serialization.Serializable


@Serializable
data class UpdateUsuarioForm (
    @field:Size(min = 1, message = "Informe o nome.")
    val nome: String? = null,

    @field:Email(message = "Informe um email válido.")
    var email: String? = null,

    @field:Valid // Para validação em cascata
    val endereco: UpdateEnderecoForm? = null
)