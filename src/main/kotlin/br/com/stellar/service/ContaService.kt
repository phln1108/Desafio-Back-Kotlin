package br.com.stellar.service

import br.com.stellar.entities.Agencia
import br.com.stellar.entities.Conta
import br.com.stellar.entities.TipoConta
import br.com.stellar.entities.Usuario
import br.com.stellar.exceptions.NotFoundException
import br.com.stellar.form.CreateContaForm
import br.com.stellar.form.UpdateContaForm
import br.com.stellar.model.ContaDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.eclipse.microprofile.jwt.JsonWebToken
import java.time.LocalDateTime


@ApplicationScoped
class ContaService(@Inject var jwt: JsonWebToken) {

    @Transactional
    fun create(form: CreateContaForm): ContaDTO {
        val agencia = Agencia.findById(form.agenciaId)
        val usuario = Usuario.findById(form.usuarioId)
        val tipoDeConta = TipoConta.fromString(form.tipoDeConta)

        if (agencia == null || agencia.deletedAt != null) throw NotFoundException("Agencia não encontrada")
        if (usuario == null || usuario.deletedAt != null) throw NotFoundException("Usuário não encontrado")

        //verifica se o usuario ja possui uma conta nessa agencia
        val contaExistente =
            Conta.find("agencia = ?1 and usuario = ?2 and deletedAt IS NULL", agencia, usuario).firstResult()
        if (contaExistente != null) {
            throw IllegalArgumentException("Usuário já possui uma conta na agência: " + agencia.nome)
        }

        val conta = Conta.create(form, agencia, usuario, tipoDeConta)
        conta.persist()

        return conta.toDTO()
    }

    fun listAll(): List<ContaDTO> = Conta.find("deletedAt IS NULL").list().map { it.toDTO() }

    fun listAllByUsuarioId(): List<ContaDTO> {
        val id = jwt.getClaim<Long>("id")

        return Conta.find("deletedAt IS NULL AND usuario.id = ?1", id).list().map { it.toDTO() }

    }

    fun listById(id: Long): ContaDTO {
        val conta = Conta.findById(id)

        if (conta == null || conta.deletedAt != null) throw NotFoundException("Conta não encontrada")

        return conta.toDTO()
    }

    fun listByNumber(number: String): ContaDTO {
        val conta = Conta.find("number", number).firstResult()

        if (conta == null || conta.deletedAt != null) throw NotFoundException("Conta não encontrado")

        return conta.toDTO()
    }

    @Transactional
    fun update(id: Long, form: UpdateContaForm): ContaDTO {
        val conta = Conta.findById(id)

        if (conta == null || conta.deletedAt != null) throw NotFoundException("Conta não encontrado")

        form.tipoDeConta?.let {
            val tipoDeConta = TipoConta.fromString(form.tipoDeConta)
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