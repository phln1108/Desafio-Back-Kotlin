package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class ContaSaldoDTO(
    var id: Long,
    var numeroCartao: String,
    var agencia: AgenciaDTO,
    var usuario: UsuarioDTO,
    val tipoDeConta: TipoDeContaDTO,

    val saldo: Float,
    val saldoCredito: Float? = null,  // Opcional (conta especial/premium)
    val saldoLis: Float? = null,      // Opcional (conta premium)
    val limiteCredito: Float? = null,
    val limiteLis: Float? = null,
)
