package br.com.stellar.service

import br.com.stellar.entities.Banco
import br.com.stellar.entities.Conta
import br.com.stellar.entities.Transacao
import br.com.stellar.exceptions.NotFoundException
import br.com.stellar.exceptions.OperacaoNaoPermitidaException
import br.com.stellar.exceptions.SaldoInsuficienteException
import br.com.stellar.form.CreateTransacaoForm
import br.com.stellar.model.BancoDTO
import br.com.stellar.model.TransacaoDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.LocalDateTime


@ApplicationScoped
class TransacaoService {

    @Transactional
    fun create(form: CreateTransacaoForm): TransacaoDTO {
        val remetente = Conta.findById(form.remetenteId)
        val destinatario = Conta.findById(form.destinatarioId)

        if (remetente == null || remetente.deletedAt != null) throw NotFoundException("Conta remetente não encontrada")
        if (destinatario == null || destinatario.deletedAt != null) throw NotFoundException("Conta destinatária não encontrada")

        if(form.usouCredito) {
            if (remetente.tipoDeConta.nome == "Padrao")
                throw OperacaoNaoPermitidaException("Apenas contas Especial e Premium realiza pagamento em crédito.")

            if (form.valor < remetente.saldoCredito)
                throw SaldoInsuficienteException("Saldo de Crédito insuficiente.")

            remetente.saldoCredito -= form.valor
            destinatario.saldo += form.valor

        }else{
            if (form.valor < remetente.saldo) {
                if (remetente.tipoDeConta.nome != "Premium")
                    throw SaldoInsuficienteException("Saldo insuficiente.")

                var saldoGasto = remetente.saldo
                var valorRestante = form.valor - saldoGasto

                if (valorRestante > remetente.saldoLis)
                    throw SaldoInsuficienteException("Saldo de LIS insuficiente.")

                remetente.saldo -= saldoGasto // zerando o saldo da conta
                remetente.saldoLis -= valorRestante //retirando o valor restante do saldo Lis
                destinatario.saldo += form.valor
            } else {

                remetente.saldo -= form.valor
                destinatario.saldo += form.valor
            }
        }

        remetente.persist()
        destinatario.persist()

        val transacao = Transacao.create(form, remetente, destinatario)
        transacao.persist()

        return transacao.toDTO()
    }

    fun listAdllFromConta(contaId: Long): List<TransacaoDTO> = Transacao.find("(remetente.id = ?1 or destinatario.id = ?1) and deletedAt IS NULL",contaId).list().map { it.toDTO() }

    fun listAll(): List<TransacaoDTO> = Transacao.find("deletedAt IS NULL").list().map { it.toDTO() }

    @Transactional
    fun delete(id: Long) {
        val transacao = Transacao.findById(id)

        if (transacao == null || transacao.deletedAt != null) throw NotFoundException("Trasacao não encontrado")

        transacao.deletedAt = LocalDateTime.now()
        transacao.persist()
    }
}