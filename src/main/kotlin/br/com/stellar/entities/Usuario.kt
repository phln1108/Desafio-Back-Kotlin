package br.com.stellar.entities

import jakarta.persistence.*
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import br.com.stellar.form.CreateUsuarioForm
import br.com.stellar.model.UsuarioDTO
import java.time.LocalDateTime

@Entity
@Table(name = "USUARIO")
class Usuario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        updatable = false,
    )
    var id: Long,

    var nome: String,

    @Column(unique = true)
    var email: String,

    var senha: String,

    @Embedded
    var endereco: Endereco,

    var isAdmin: Boolean,

    var isSuperAdmin: Boolean,

    @Column(name = "created_at")
    var createdAt: LocalDateTime,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null

) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        nome = "",
        email = "",
        senha = "",
        endereco = Endereco(),
        isAdmin = false,
        isSuperAdmin = false,
        createdAt = LocalDateTime.now(),
        deletedAt = null
    )

    fun toDTO(): UsuarioDTO = UsuarioDTO(
        id = this.id,
        nome = this.nome,
        email = this.email,
        endereco = endereco.toDTO()
    )

    companion object : PanacheCompanion<Usuario> {

        fun create(form: CreateUsuarioForm, senhaHash: String): Usuario {

            return Usuario().apply {
                this.nome = form.nome
                this.email = form.email
                this.senha = senhaHash
                this.isAdmin = form.isAdmin

                this.endereco = Endereco(
                    form.endereco.logradouro,
                    form.endereco.numero,
                    form.endereco.complemento,
                    form.endereco.cidade,
                    form.endereco.estado,
                    form.endereco.cep,
                )
            }
        }
    }

}
