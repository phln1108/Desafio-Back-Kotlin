package br.com.stellar.service

import br.com.stellar.entities.Agencia
import br.com.stellar.entities.Banco
import br.com.stellar.entities.Conta
import br.com.stellar.entities.TipoDeConta
import br.com.stellar.entities.Usuario
import br.com.stellar.exceptions.NotFoundException
import br.com.stellar.form.CreateContaForm
import br.com.stellar.form.UpdateAgenciaForm
import br.com.stellar.form.UpdateContaForm
import br.com.stellar.model.AgenciaDTO
import br.com.stellar.model.ContaDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.time.LocalDateTime


@ApplicationScoped
class ContaService() {

    @Transactional
    fun create(form: CreateContaForm): ContaDTO {
        val agencia = Agencia.findById(form.agenciaId)
        val usuario = Usuario.findById(form.usuarioId)
        val tipoDeConta = TipoDeConta.findById(form.tipoDeContaId)

        if (agencia == null || agencia.deletedAt != null) throw NotFoundException("Agencia não encontrada")
        if (usuario == null || usuario.deletedAt != null) throw NotFoundException("Usuário não encontrado")
        if (tipoDeConta == null) throw NotFoundException("TipoDeConta não encontrado")

        val conta = Conta.create(agencia, usuario, tipoDeConta)
        conta.persist()

        return conta.toDTO()
    }

    fun listAll(): List<ContaDTO> = Conta.find("deletedAt IS NULL").list().map { it.toDTO() }

    fun listById(id: Long): ContaDTO {
        val conta = Conta.findById(id)

        if (conta == null || conta.deletedAt != null) throw NotFoundException("Conta não encontrado")

        return conta.toDTO()
    }

    @Transactional
    fun update(id: Long, form: UpdateContaForm): ContaDTO {
        val conta = Conta.findById(id)

        if (conta == null || conta.deletedAt != null) throw NotFoundException("Conta não encontrado")

        form.tipoDeContaId?.let {
            val tipoDeConta = TipoDeConta.findById(form.tipoDeContaId)
            if (tipoDeConta == null) throw NotFoundException("TipoDeConta não encontrado")
            conta.tipoDeConta = tipoDeConta
        }

        conta.persist()

        return conta.toDTO()
    }

    @Transactional
    fun delete(id: Long) {
        val conta = Conta.findById(id)

        if (conta == null || conta.deletedAt != null) throw NotFoundException("Conta não encontrado")

        conta.deletedAt = LocalDateTime.now()
        conta.persist()
    }


}