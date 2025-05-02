package br.com.stellar.entities

import br.com.stellar.form.CreateContaForm
import br.com.stellar.model.ContaDTO
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "CONTA")
class Conta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        updatable = false,
    )
    var id: Long,

    @ManyToOne
    @JoinColumn(name = "agencia_id")
    var agencia: Agencia,

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    var usuario: Usuario,

    var saldo: Float = 0.0F,

    @Column(name = "saldo_credito")
    var saldoCredito: Float = 0F,

    @Column(name = "saldo_lis")
    var saldoLis: Float = 0F,

    @ManyToOne
    @JoinColumn(name = "tipo_de_conta_id")
    var tipoDeConta: TipoDeConta,

    @Column(name = "created_at")
    var createdAt: LocalDateTime,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null

) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        agencia = Agencia(),
        usuario = Usuario(),
        saldo = 0F,
        saldoCredito = 0F,
        saldoLis = 0F,
        tipoDeConta = TipoDeConta(),
        createdAt = LocalDateTime.now(),
        deletedAt = null
    )

    fun toDTO(): ContaDTO  = ContaDTO(
        id = this.id,
        agencia = this.agencia.toDTO(),
        usuario = this.usuario.toDTO(),
        tipoDeConta = this.tipoDeConta.toDTO(),
    )

    companion object : PanacheCompanion<Conta> {
        fun create(agencia: Agencia, usuario: Usuario, tipoDeConta: TipoDeConta): Conta {
            return Conta().apply {
                this.agencia = agencia
                this.usuario = usuario
                this.tipoDeConta = tipoDeConta
            }
        }
    }
}