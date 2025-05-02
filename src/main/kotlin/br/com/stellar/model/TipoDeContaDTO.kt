package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class TipoDeContaDTO(
    var id: Long,
    var nome: String
)