package br.com.stellar.service

import br.com.stellar.entities.Banco
import br.com.stellar.exceptions.NotFoundException
import br.com.stellar.form.CreateBancoForm
import br.com.stellar.form.UpdateBancoForm
import br.com.stellar.model.BancoDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.LocalDateTime

@ApplicationScoped
class BancoService {

    @Transactional
    fun create(form: CreateBancoForm): BancoDTO {
        val banco = Banco.create(form)
        banco.persist()

        return banco.toDTO()
    }

    fun listall(): List<BancoDTO> = Banco.find("deletedAt IS NULL").list().map { it.toDTO() }

    fun listById(id: Long): BancoDTO {
        val banco = Banco.findById(id)

        if (banco == null || banco.deletedAt != null) throw NotFoundException("Banco não encontrado")

        return banco.toDTO()
    }

    @Transactional
    fun update(id: Long, form: UpdateBancoForm): BancoDTO {
        val banco = Banco.findById(id)

        if (banco == null || banco.deletedAt != null) throw NotFoundException("Banco não encontrado")

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
    fun delete(id: Long) {
        val banco = Banco.findById(id)

        if (banco == null || banco.deletedAt != null) throw NotFoundException("Banco não encontrado")

        banco.deletedAt = LocalDateTime.now()
        banco.persist()
    }
}
