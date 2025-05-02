package br.com.stellar.entities

import br.com.stellar.form.CreateTransacaoForm
import br.com.stellar.model.TransacaoDTO
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity()
@Table(name = "TRANSACAO")
class Transacao(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        updatable = false,
    ) var id: Long,

    @ManyToOne
    @JoinColumn(
        name = "remetente_conta_id",
        nullable = false,
        updatable = false,
    ) var remetente: Conta,

    @ManyToOne
    @JoinColumn(
        name = "destinatario_conta_id",
        nullable = false,
        updatable = false,
    ) var destinatario: Conta,

    @Column(
        nullable = false,
        updatable = false,
    ) var valor: Float = 0F,

    @Column(
        name = "pagamento_em_credito",
        nullable = false,
        updatable = false,
    ) var usouCredito: Boolean = false,

    @Column(
        name = "valor_lis_utilizado",
        updatable = false,
    ) var valorLis: Float? = null,

    @Column(
        name = "data_de_transacao",
        nullable = false,
        updatable = false,
    ) var dataTransacao: LocalDateTime,

    @Column(name = "created_at")
    var createdAt: LocalDateTime,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null


) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        remetente = Conta(),
        destinatario = Conta(),
        valor = 0F,
        dataTransacao = LocalDateTime.now(),
        createdAt = LocalDateTime.now(),
        deletedAt = null
    )

    fun toDTO(): TransacaoDTO = TransacaoDTO(
        id = this.id,
        remetente = this.remetente.toDTO(),
        destinatario = this.destinatario.toDTO(),
        valor = this.valor,
        usouCredito = false,
        dataTransacao = this.dataTransacao
    )

    companion object : PanacheCompanion<Transacao> {
        fun create(form: CreateTransacaoForm, remetente: Conta, destinatario: Conta): Transacao {
            return Transacao().apply {
                this.remetente = remetente
                this.destinatario = destinatario
                this.valor = form.valor
                this.usouCredito = form.usouCredito
                this.valorLis = form.valorLis
            }
        }
    }

}