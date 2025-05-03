package br.com.stellar.service

import br.com.stellar.entities.Transacao
import br.com.stellar.entities.Usuario
import br.com.stellar.exceptions.BadRequestException
import br.com.stellar.exceptions.NotFoundException
import br.com.stellar.form.CreateUsuarioForm
import br.com.stellar.form.UpdateUsuarioForm
import br.com.stellar.model.UsuarioDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.time.LocalDateTime

@ApplicationScoped
class UsuarioService {

    fun create(form: CreateUsuarioForm): UsuarioDTO {
        if (Usuario.find("email", form.email).count() > 0) {
            throw BadRequestException("Email já cadastrado")
        }
        
        val usuario = Usuario.create(form)
        usuario.persist()

        return usuario.toDTO()
    }

    fun listAll(): List<UsuarioDTO> = Usuario.findAll().list().map { it.toDTO() }

    fun listById(id: Long): UsuarioDTO {
        val usuario = Usuario.findById(id)

        if (usuario == null || usuario.deletedAt != null) throw NotFoundException("Usuario não encontrado")

        return usuario.toDTO()
    }

    @Transactional
    fun update(id: Long, form: UpdateUsuarioForm): UsuarioDTO {
        val usuario = Usuario.findById(id)

        if (usuario == null || usuario.deletedAt != null) throw NotFoundException("Usuario não encontrado")

        usuario.apply {
            form.nome?.let { nome = it }

            form.email?.let { email = it }

            form.endereco?.let {
                endereco.logradouro = it.logradouro ?: endereco.logradouro
                endereco.numero = it.numero ?: endereco.numero
                endereco.cidade = it.cidade ?: endereco.cidade
                endereco.estado = it.estado ?: endereco.estado
                endereco.cep = it.cep ?: endereco.cep
            }
        }

        Usuario.persist(usuario)

        return usuario.toDTO()
    }

    @Transactional
    fun delete(id: Long) {
        val usuario = Usuario.findById(id)

        if (usuario == null || usuario.deletedAt != null) throw NotFoundException("Usuario não encontrado")

        usuario.deletedAt = LocalDateTime.now()
        usuario.persist()
    }
}
