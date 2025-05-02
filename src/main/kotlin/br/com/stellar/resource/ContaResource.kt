package br.com.stellar.resource

import br.com.stellar.form.CreateContaForm
import br.com.stellar.form.CreateUsuarioForm
import br.com.stellar.form.UpdateContaForm
import br.com.stellar.form.UpdateUsuarioForm
import br.com.stellar.service.ContaService
import jakarta.inject.Inject
import jakarta.validation.Valid
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam

class ContaResource (@Inject var contaService: ContaService){
    @POST
    @Path("/novo")
    fun create(
        @Valid form: CreateContaForm
    ) = contaService.create(form)

    @GET
    @Path("/all")
    fun listAll() = contaService.listAll()

    @GET
    @Path("/{id}")
    fun listById(
        @PathParam("id") id: Long
    ) = contaService.listById(id)

    @PUT
    @Path("/{id}")
    fun update(
        @PathParam("id") id: Long,
        @Valid form: UpdateContaForm
    ) = contaService.update(id, form)

    @DELETE
    @Path("/{id}")
    fun delete(
        @PathParam("id") id: Long
    ) = contaService.delete(id)
}