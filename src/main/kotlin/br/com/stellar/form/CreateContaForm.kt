package br.com.stellar.form

import jakarta.validation.constraints.NotNull
import kotlinx.serialization.Serializable

@Serializable
data class CreateContaForm(
    @field:NotNull(message = "Informe a agencia.")
    val agenciaId: Long,

    @field:NotNull(message = "Informe o usuario.")
    val usuarioId:Long,

    val tipoDeContaId: Long = 1
)
