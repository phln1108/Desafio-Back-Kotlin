package br.com.stellar.resource

import br.com.stellar.entities.Transacao
import br.com.stellar.form.CreateTransacaoForm
import br.com.stellar.form.CreateUsuarioForm
import br.com.stellar.form.UpdateUsuarioForm
import br.com.stellar.service.TransacaoService
import br.com.stellar.service.UsuarioService
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


@Path("/transferencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TransacaoResource(@Inject var transacaoService: TransacaoService) {

    @POST
    @Path("/novo")
    fun create(
        @Valid form: CreateTransacaoForm
    ) = transacaoService.create(form)

    @GET
    @Path("/all/{id}")
    fun listAll(
        @PathParam("id") id: Long
    ) = transacaoService.listAdllFromConta(id)


    @DELETE
    @Path("/{id}")
    fun delete(
        @PathParam("id") id: Long
    ) = transacaoService.delete(id)

}