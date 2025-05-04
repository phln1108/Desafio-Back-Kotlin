package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginDTO(
    val token: String,
    val usuario: UsuarioDTO
)