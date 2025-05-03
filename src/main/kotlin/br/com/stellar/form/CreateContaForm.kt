package br.com.stellar.form

import jakarta.persistence.Column
import jakarta.validation.constraints.NotNull
import kotlinx.serialization.Serializable

@Serializable
data class CreateContaForm(
    @field:NotNull(message = "Informe a agencia.")
    val agenciaId: Long,

    @field:NotNull(message = "Informe o usuario.")
    val usuarioId:Long,

    @field:NotNull(message = "Informe o tipo de conta.")
    val tipoDeContaId: Long = 1,

    val limiteCredito: Float? = null,

    val limiteLis: Float? = null,
)
