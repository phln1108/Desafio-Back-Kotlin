package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class AgenciaDTO (
    var id: Long,
    var nome: String,
    var endereco: EnderecoDTO,
    var banco: BancoDTO,
)