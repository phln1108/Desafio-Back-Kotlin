package br.com.stellar.form

import jakarta.validation.constraints.NotNull
import kotlinx.serialization.Serializable


@Serializable
data class CreateTransacaoForm(
    @field:NotNull(message = "Informe a Conta remetente")
    var remetenteId: Long,

    @field:NotNull(message = "Informe a Conta destinatario")
    var destinatarioId: Long,

    @field:NotNull(message = "Informe uma quantia")
    var valor: Float,

    var usouCredito: Boolean = false,

    var valorLis: Float? = null
)
