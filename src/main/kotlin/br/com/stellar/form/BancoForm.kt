package br.com.stellar.form

import br.com.stellar.entities.Banco
import br.com.stellar.serialization.LocalDateTimeJson
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import kotlinx.serialization.Serializable

@Serializable
data class BancoForm(
    @field:NotBlank(message = "Informe o nome.") val nome: String,
    @field:NotNull(message = "Informe a data de nascimento.") @field:PastOrPresent(message = "A data de fundação não pode ser uma data futura.") val dataFundacao: LocalDateTimeJson
)

