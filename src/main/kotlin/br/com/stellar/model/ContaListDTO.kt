package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class ContaListDTO (
    var contas: List<ContaDTO>
)