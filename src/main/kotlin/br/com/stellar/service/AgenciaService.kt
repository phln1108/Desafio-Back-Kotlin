package br.com.stellar.service

import br.com.stellar.entities.Agencia
import br.com.stellar.entities.Banco
import br.com.stellar.exceptions.NotFoundException
import br.com.stellar.form.AgenciaForm
import br.com.stellar.form.AgenciaUpdateForm
import br.com.stellar.model.AgenciaDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@ApplicationScoped
class AgenciaService() {

    @Transactional
    fun create(form: AgenciaForm): AgenciaDTO {
        val banco = Banco.findById(form.bancoId) ?: throw NotFoundException("Banco não encontrado")

        val agencia = Agencia.create(form, banco)
        agencia.persist()

        return agencia.toDTO()
    }

    fun listAll(): List<AgenciaDTO> = Agencia.findAll().list().map { it.toDTO() }

    fun listById(id: Long): AgenciaDTO {
        val Agencia = Agencia.findById(id) ?: throw NotFoundException("Agencia não encontrado")
        return Agencia.toDTO()
    }

    @Transactional
    fun update(id: Long, form: AgenciaUpdateForm): AgenciaDTO {
        val agencia = Agencia.findById(id) ?: throw NotFoundException("Agencia não encontrado")

        agencia.apply {
            form.nome?.let { nome = it }
            form.bancoId?.let {
                banco = Banco.findById(form.bancoId) ?: throw NotFoundException("Banco não encontrado")
            }
            form.endereco?.let {
                it.logradouro?.let {
                    endereco.logradouro = it
                }
                it.numero?.let {
                    endereco.numero = it
                }
                it.cidade?.let {
                    endereco.cidade = it
                }
                it.estado?.let {
                    endereco.estado = it
                }
                it.cep?.let {
                    endereco.cep = it
                }
            }
        }

        Agencia.persist(agencia)

        return agencia.toDTO()
    }

    @Transactional
    fun delete(id: Long): JsonObject {
        val agencia = Agencia.findById(id) ?: throw NotFoundException("Agencia não encontrado")
        agencia.delete()
        return buildJsonObject {
            put("message", "Agencia deletado com sucesso.")
        }
    }
}