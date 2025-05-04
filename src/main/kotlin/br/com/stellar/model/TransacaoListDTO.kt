package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class TransacaoListDTO (
    var transacoes: List<TransacaoDTO>
)