package br.com.stellar.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import br.com.stellar.form.AgenciaForm
import br.com.stellar.model.AgenciaDTO
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*

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
    @JoinColumn(name = "banco_id",foreignKey = ForeignKey(name = "fk_agencia_banco"))
    var banco: Banco,

    var nome: String,

    @Embedded
    var endereco: Endereco,

): PanacheEntityBase {

    constructor() : this(
        id = 0,
        banco = Banco(),
        nome = "",
        endereco = Endereco(),
    )

    fun toDTO(): AgenciaDTO = AgenciaDTO(
        id = this.id,
        nome = this.nome,
        banco = this.banco.toDTO(), // Para incluir o ID do banco
        endereco = this.endereco.toDTO() // Supondo que você tenha o método toDTO em Endereco
    )

    companion object : PanacheCompanion<Agencia> {

        fun create(form: AgenciaForm, banco: Banco): Agencia {
            return Agencia().apply {
                this.banco = banco
                nome = form.nome
                endereco = Endereco(
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