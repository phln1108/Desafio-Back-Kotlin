package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class ContaSaldoDTO(
    val id: Long,
    val agencia: AgenciaDTO,
    val usuario: UsuarioDTO,
    val saldo: Float,
    val saldoCredito: Float? = null,  // Opcional (conta especial/premium)
    val saldoLis: Float? = null,      // Opcional (conta premium)
    val tipoDeConta: TipoDeContaDTO
)
