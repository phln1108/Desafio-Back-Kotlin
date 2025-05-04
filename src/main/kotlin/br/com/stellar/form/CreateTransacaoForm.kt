package br.com.stellar.form

import jakarta.validation.constraints.NotNull
import kotlinx.serialization.Serializable


@Serializable
data class CreateTransacaoForm(
    var remetenteNumero: String? = null,

    @field:NotNull(message = "Informe a Conta destinatario")
    var destinatarioNumero: String,

    @field:NotNull(message = "Informe uma quantia")
    var valor: Float,

    @field:NotNull(message = "Informe o tipo de transação")
    var tipoTransacao: String,

    var usouCredito: Boolean = false,

    var valorLis: Float? = null
)
