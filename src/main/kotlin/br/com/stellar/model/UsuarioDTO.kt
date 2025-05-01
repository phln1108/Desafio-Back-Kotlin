package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioDTO (
    val id: Long,
    val nome: String,
    val email: String,
    val endereco: EnderecoDTO
)