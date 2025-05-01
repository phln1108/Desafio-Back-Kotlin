package br.com.stellar.entities

import br.com.stellar.model.EnderecoDTO
import jakarta.persistence.Embeddable

@Embeddable
data class Endereco(
    var logradouro: String,
    var numero: String,
    var complemento: String? = null,
    var cidade: String,
    var estado: String,
    var cep: String
) {
    constructor() : this(
        logradouro = "",
        numero = "",
        complemento = "",
        cidade = "",
        estado = "",
        cep = ""
    );

    fun toDTO(): EnderecoDTO = EnderecoDTO(
        logradouro = this.logradouro,
        numero = this.numero,
        complemento = this.complemento,
        cidade = this.cidade,
        estado = this.estado,
        cep = this.cep
    )
}