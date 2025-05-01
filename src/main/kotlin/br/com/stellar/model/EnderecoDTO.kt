package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class EnderecoDTO (
    val logradouro: String,
    val numero: String,
    val complemento: String? = null,
    val cidade: String,
    val estado: String,
    val cep: String
)
