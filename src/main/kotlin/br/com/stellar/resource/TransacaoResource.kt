package br.com.stellar.resource

import br.com.stellar.entities.Transacao
import br.com.stellar.form.CreateTransacaoForm
import br.com.stellar.form.CreateUsuarioForm
import br.com.stellar.form.UpdateUsuarioForm
import br.com.stellar.service.TransacaoService
import br.com.stellar.service.UsuarioService
import jakarta.annotation.security.RolesAllowed
import jakarta.inject.Inject
import jakarta.validation.Valid
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.jwt.JsonWebToken

@Path("/transacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TransacaoResource(
    @Inject var transacaoService: TransacaoService,
) {

    @POST
    @Path("/transferencia")
    @RolesAllowed("user")
    fun transferir(
        @Valid form: CreateTransacaoForm
    ) = transacaoService.transferir(form)

    @POST
    @Path("/saque")
    @RolesAllowed("user")
    fun saquar(
        @Valid form: CreateTransacaoForm
    ) = transacaoService.saquar(form)

    @POST
    @Path("/deposito")
    @RolesAllowed("user")
    fun depositar(
        @Valid form: CreateTransacaoForm
    ) = transacaoService.depositar(form)

    @GET
    @Path("/all")
    @RolesAllowed("admin")
    fun listAll(): Response {
        val transacoes = transacaoService.listAll()
        return Response.ok(transacoes).build()
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    fun delete(
        @PathParam("id") id: Long
    ) = transacaoService.delete(id)

}