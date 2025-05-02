package br.com.stellar.form

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import kotlinx.serialization.Serializable

@Serializable
data class UpdateEnderecoForm(
    @field:Size(min = 1,message = "Informe o logradouro.")
    val logradouro: String? = null,

    @field:Size(min = 1,message = "Informe o número.")
    val numero: String? = null,

    val complemento: String? = null,

    @field:Size(min = 1,message = "Informe a cidade.")
    val cidade: String? = null,

    @field:Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres.")
    val estado: String? = null,

    @field:Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido.")
    val cep: String? = null
)