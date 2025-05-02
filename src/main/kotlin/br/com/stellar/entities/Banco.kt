package br.com.stellar.entities

import br.com.stellar.form.CreateBancoForm
import br.com.stellar.model.BancoDTO
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "BANCO")
class Banco(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        updatable = false,
    ) var id: Long,

    var nome: String,

    @Column(name = "data_fundacao")
    var dataFundacao: LocalDateTime,

    @Column(name = "created_at")
    var createdAt: LocalDateTime,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null

) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        nome = "",
        dataFundacao = LocalDateTime.now(),
        createdAt = LocalDateTime.now(),
        deletedAt = null,
    )

    fun toDTO(): BancoDTO = BancoDTO(
        id = this.id,
        nome = this.nome,
        dataFundacao = this.dataFundacao
    )

    companion object : PanacheCompanion<Banco> {

        fun create(form: CreateBancoForm): Banco {
            return Banco().apply {
                this.nome = form.nome
                this.dataFundacao = form.dataFundacao
            }
        }

    }

}