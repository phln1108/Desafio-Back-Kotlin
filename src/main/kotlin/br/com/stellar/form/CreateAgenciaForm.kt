package br.com.stellar.form

import kotlinx.serialization.Serializable
import jakarta.validation.constraints.*
import jakarta.validation.Valid

@Serializable
data class CreateAgenciaForm(
    @field:NotNull(message = "Informe o banco.")
    val bancoId: Long,

    @field:NotBlank(message = "Informe o nome da agência.")
    val nome: String,

    @field:Valid // Para validação em cascata
    val endereco: CreateEnderecoForm
)

