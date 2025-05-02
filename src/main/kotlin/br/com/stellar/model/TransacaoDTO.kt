package br.com.stellar.model

import br.com.stellar.serialization.LocalDateTimeJson
import kotlinx.serialization.Serializable

@Serializable
data class TransacaoDTO(
    var id: Long,
    var remetente: ContaDTO,
    var destinatario: ContaDTO,
    var valor: Float,
    var usouCredito: Boolean,
    var ValorList: Float? = null,
    var dataTransacao: LocalDateTimeJson
)
