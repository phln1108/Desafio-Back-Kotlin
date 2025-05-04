package br.com.stellar.form

import jakarta.validation.constraints.NotNull
import kotlinx.serialization.Serializable


@Serializable
data class CreateTransacaoForm(
    var remetenteId: Long? = null,

    @field:NotNull(message = "Informe a Conta destinatario")
    var destinatarioId: Long,

    @field:NotNull(message = "Informe uma quantia")
    var valor: Float,

    @field:NotNull(message = "Informe o tipo de transação")
    var tipoTransacao: String,

    var usouCredito: Boolean = false,

    var valorLis: Float? = null
)
