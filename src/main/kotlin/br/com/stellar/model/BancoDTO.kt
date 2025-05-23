package br.com.stellar.model

import br.com.stellar.serialization.LocalDateTimeJson
import kotlinx.serialization.Serializable

@Serializable
data class BancoDTO(
    var id: Long,
    var nome: String,
    var dataFundacao: LocalDateTimeJson
)