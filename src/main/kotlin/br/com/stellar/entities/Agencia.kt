package br.com.stellar.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import br.com.stellar.form.AgenciaForm
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "AGENCIA")
class Agencia(

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(
        nullable = false, 
        updatable = false, 
    ) 
    var id: Long, 
    
    @ManyToOne
    @JoinColumn(name = "banco_id")
    var banco: Banco,

    var nome: String,

    @Column(name = "data_criacao")
    var dataCriacao: LocalDateTime

): PanacheEntityBase {

    constructor() : this(
        id = 0,
        banco = Banco(),
        nome = "",
        dataCriacao = LocalDateTime.now()
    )

    companion object : PanacheCompanion<Agencia> {

        fun create(form: AgenciaForm, banco: Banco): Agencia {
            return Agencia(
                id = 0,
                banco = banco,
                nome = form.nome,
                dataCriacao = LocalDateTime.now()
            )
        }

    }

}