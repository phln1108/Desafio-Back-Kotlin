package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class ContaSaldoListDTO(
    val contas: List<ContaSaldoDTO>
)
