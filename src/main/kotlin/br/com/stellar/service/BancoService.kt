package br.com.stellar.service

import br.com.stellar.entities.Banco
import br.com.stellar.exceptions.NotFoundException
import br.com.stellar.form.BancoForm
import br.com.stellar.form.BancoUpdateForm
import br.com.stellar.model.BancoDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@ApplicationScoped
class BancoService {

    @Transactional
    fun criarBanco(form: BancoForm): BancoDTO {
        val banco = Banco.create(form)
        banco.persist()

        return banco.toDTO()
    }

    fun listBancos(): List<BancoDTO> = Banco.findAll().list().map { it.toDTO() }

    fun listById(id: Long): BancoDTO {
        val banco = Banco.findById(id) ?: throw NotFoundException("Banco não encontrado")
        return banco.toDTO()
    }

    @Transactional
    fun updateBanco(id: Long, form: BancoUpdateForm): BancoDTO {
        val banco = Banco.findById(id) ?: throw NotFoundException("Banco não encontrado")

        form.nome?.let {
            banco.nome = it
        }

        form.dataFundacao?.let {
            banco.dataFundacao = it
        }

        Banco.persist(banco)

        return banco.toDTO()
    }

    @Transactional
    fun delete(id: Long): JsonObject {
        val banco = Banco.findById(id) ?: throw NotFoundException("Banco não encontrado")
        banco.delete()
        return buildJsonObject {
            put("message", "Banco deletado com sucesso.")
        }
    }
}
