package br.com.stellar.entities

import br.com.stellar.model.TipoDeContaDTO
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import io.quarkus.runtime.StartupEvent
import jakarta.annotation.PostConstruct
import jakarta.enterprise.event.Observes
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityManager
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.transaction.Transactional

@Entity
@Table(name = "TIPO_DE_CONTA")
class TipoDeConta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long = 0,

    @Column(nullable = false, unique = true)
    var nome: String = ""
) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        nome = ""
    )

    fun toDTO(): TipoDeContaDTO = TipoDeContaDTO (
        id = this.id,
        nome = this.nome,
    )

    companion object : PanacheCompanion<TipoDeConta> {
        const val PADRAO = "Padrao"
        const val ESPECIAL = "Especial"
        const val PREMIUM = "Premium"
    }
}

//criação estatico dos tipos de conta
@Singleton
class TipoContaInitializer {

    @Inject
    lateinit var entityManager: EntityManager

    @Transactional
    fun onStart(@Observes event: StartupEvent) {
        criarTipoSeNaoExistir(TipoDeConta.PADRAO)
        criarTipoSeNaoExistir(TipoDeConta.ESPECIAL)
        criarTipoSeNaoExistir(TipoDeConta.PREMIUM)
    }

    private fun criarTipoSeNaoExistir(nomeTipo: String) {
        if (TipoDeConta.find("nome", nomeTipo).list().isEmpty()) {
            val tipo = TipoDeConta()
            tipo.nome = nomeTipo
            entityManager.persist(tipo)
        }
    }
}