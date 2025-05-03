package br.com.stellar.entities

import br.com.stellar.form.CreateContaForm
import br.com.stellar.model.ContaDTO
import br.com.stellar.model.ContaSaldoDTO
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
    ) var id: Long,

    @Column(
        name = "numero_cartao",
        unique = true,
        nullable = false,
        updatable = false
    ) var numeroCartao: String,

    @ManyToOne
    @JoinColumn(name = "agencia_id")
    var agencia: Agencia,

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    var usuario: Usuario,

    @Column(name = "limite_Credito")
    var limiteCredito: Float = 0F,

    @Column(name = "limite_Lis")
    var limiteLis: Float = 0F,

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
        numeroCartao = "",
        agencia = Agencia(),
        usuario = Usuario(),
        saldo = 0F,
        saldoCredito = 0F,
        saldoLis = 0F,
        tipoDeConta = TipoDeConta(),
        createdAt = LocalDateTime.now(),
        deletedAt = null,
        limiteCredito = 0F,
        limiteLis = 0F
    )

    fun toDTO(): ContaDTO = ContaDTO(
        id = this.id,
        numeroCartao = this.numeroCartao,
        agencia = this.agencia.toDTO(),
        usuario = this.usuario.toDTO(),
        tipoDeConta = this.tipoDeConta.toDTO(),
    )

    fun toSaldoDTO(): ContaSaldoDTO = ContaSaldoDTO(
        id = this.id,
        numeroCartao = this.numeroCartao,
        agencia = this.agencia.toDTO(),
        usuario = this.usuario.toDTO(),
        tipoDeConta = this.tipoDeConta.toDTO(),
        saldo = this.saldo,
        saldoCredito = this.saldoCredito,
        saldoLis = this.saldoLis,
        limiteCredito = this.limiteCredito,
        limiteLis = this.limiteLis,
    )

    companion object : PanacheCompanion<Conta> {
        fun create(form: CreateContaForm, agencia: Agencia, usuario: Usuario, tipoDeConta: TipoDeConta): Conta {
            val numeroCartao = generateUniqueNumeroCartao()
            return Conta().apply {
                this.agencia = agencia
                this.usuario = usuario
                this.tipoDeConta = tipoDeConta
                this.numeroCartao = numeroCartao
                this.createdAt = LocalDateTime.now()
                this.limiteCredito = form.limiteCredito ?: 0F
                this.limiteLis = form.limiteLis ?: 0F
            }
        }

        private fun generateUniqueNumeroCartao(): String {
            var numero: String
            do {
                numero = (10000000..99999999).random().toString()
            } while (Conta.find("numeroCartao", numero).count() > 0)
            return numero
        }
    }
}