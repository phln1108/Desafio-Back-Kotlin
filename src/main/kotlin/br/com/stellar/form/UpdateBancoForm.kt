package br.com.stellar.form

import br.com.stellar.serialization.LocalDateTimeJson
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import kotlinx.serialization.Serializable

@Serializable
data class UpdateBancoForm(

    @field:Size(min = 1, message = "O nome não pode estar em branco.")
    val nome: String? = null,

    @field:PastOrPresent(message = "A data de fundação não pode estar no futuro.")
    val dataFundacao: LocalDateTimeJson? = null
)