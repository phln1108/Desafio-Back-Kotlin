package br.com.stellar.entities


import jakarta.persistence.Embeddable

@Embeddable
data class Endereco(
    val logradouro: String,
    val numero: String,
    val complemento: String? = null,
    val cidade: String,
    val estado: String,
    val cep: String
)