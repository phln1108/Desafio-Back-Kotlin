package br.com.stellar.service

import br.com.stellar.entities.Agencia
import br.com.stellar.entities.Banco
import br.com.stellar.exceptions.NotFoundException
import br.com.stellar.form.CreateAgenciaForm
import br.com.stellar.form.UpdateAgenciaForm
import br.com.stellar.model.AgenciaDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.LocalDateTime

@ApplicationScoped
class AgenciaService() {

    @Transactional
    fun create(form: CreateAgenciaForm): AgenciaDTO {
        val banco = Banco.findById(form.bancoId)

        if (banco == null || banco.deletedAt != null) throw NotFoundException("Banco não encontrado")

        val agencia = Agencia.create(form, banco)
        agencia.persist()

        return agencia.toDTO()
    }

    fun listAll(): List<AgenciaDTO> = Agencia.find("deletedAt IS NULL").list().map { it.toDTO() }

    fun listById(id: Long): AgenciaDTO {
        val agencia = Agencia.findById(id)

        if (agencia == null || agencia.deletedAt != null) throw NotFoundException("Agencia não encontrado")

        return agencia.toDTO()
    }

    @Transactional
    fun update(id: Long, form: UpdateAgenciaForm): AgenciaDTO {
        val agencia = Agencia.findById(id)

        if (agencia == null || agencia.deletedAt != null) throw NotFoundException("Agencia não encontrado")

        agencia.apply {
            form.nome?.let { nome = it }

            form.bancoId?.let {
                val getBanco = Banco.findById(form.bancoId)
                if (getBanco == null || getBanco.deletedAt != null) throw NotFoundException("Banco não encontrado")
                banco = getBanco
            }

            form.endereco?.let {
                endereco.logradouro = it.logradouro ?: endereco.logradouro
                endereco.numero = it.numero ?: endereco.numero
                endereco.cidade = it.cidade ?: endereco.cidade
                endereco.estado = it.estado ?: endereco.estado
                endereco.cep = it.cep ?: endereco.cep
            }
        }

        Agencia.persist(agencia)

        return agencia.toDTO()
    }

    @Transactional
    fun delete(id: Long) {
        val agencia = Agencia.findById(id)

        if (agencia == null || agencia.deletedAt != null) throw NotFoundException("Agencia não encontrado")

        agencia.deletedAt = LocalDateTime.now()
        agencia.persist()
    }
}