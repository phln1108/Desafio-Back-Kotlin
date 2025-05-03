package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class ContaDTO (
    var id: Long,
    var numeroCartao: String,
    var agencia: AgenciaDTO,
    var usuario: UsuarioDTO,
    var tipoDeConta: TipoDeContaDTO
)
