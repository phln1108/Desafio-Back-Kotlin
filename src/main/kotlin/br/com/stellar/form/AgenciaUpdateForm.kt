package br.com.stellar.form

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import kotlinx.serialization.Serializable

@Serializable
data class AgenciaUpdateForm(
    val bancoId: Long? = null,

    @field:Size(min = 1, message = "Informe o nome da agência.")
    val nome: String? = null,

    @field:Valid // Para validação em cascata
    val endereco: EnderecoUpdateForm? = null
)