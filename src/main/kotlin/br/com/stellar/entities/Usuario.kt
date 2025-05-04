package br.com.stellar.entities

import jakarta.persistence.*
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import br.com.stellar.form.CreateUsuarioForm
import br.com.stellar.model.UsuarioDTO
import java.time.LocalDateTime
import io.quarkus.elytron.security.common.BcryptUtil
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.event.Observes
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional

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
        createdAt = LocalDateTime.now(),
        deletedAt = null
    )

    fun toDTO(): UsuarioDTO = UsuarioDTO(
        id = this.id,
        nome = this.nome,
        email = this.email,
        isAdmin = this.isAdmin,
        endereco = endereco.toDTO()
    )

    companion object : PanacheCompanion<Usuario> {

        fun create(form: CreateUsuarioForm): Usuario {

            return Usuario().apply {
                this.nome = form.nome
                this.email = form.email
                this.senha = BcryptUtil.bcryptHash(form.senha)

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


@Singleton
class UsuarioInitializer {
    @Transactional
    fun onStart(@Observes event: StartupEvent) {
        val adminEmail = "admin@admin.com"

        if (Usuario.find("email", adminEmail).count() == 0L) {
            val admin = Usuario().apply {
                nome = "Admin"
                email = adminEmail
                senha = BcryptUtil.bcryptHash("admin") // senha segura
                isAdmin = true
                createdAt = LocalDateTime.now()
                endereco = Endereco().apply {
                    logradouro = "Rua das ruas"
                    numero = "1000"
                    cidade = "Fortaleza"
                    estado = "CE"
                    cep = "50135-420"
                }
            }

            admin.persist()
        }
    }
}

