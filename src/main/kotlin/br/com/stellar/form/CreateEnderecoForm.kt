package br.com.stellar.form

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.Pattern
import kotlinx.serialization.Serializable

@Serializable
data class CreateEnderecoForm(
    @field:NotBlank(message = "Informe o logradouro.")
    val logradouro: String,

    @field:NotBlank(message = "Informe o número.")
    val numero: String,

    val complemento: String? = null,

    @field:NotBlank(message = "Informe a cidade.")
    val cidade: String,

    @field:NotBlank(message = "Informe o estado.")
    @field:Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres.")
    val estado: String,

    @field:NotBlank(message = "Informe o CEP.")
    @field:Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido.")
    val cep: String
)