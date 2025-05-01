package br.com.stellar.resource

import br.com.stellar.form.AgenciaForm
import br.com.stellar.form.AgenciaUpdateForm
import br.com.stellar.form.BancoUpdateForm
import br.com.stellar.service.AgenciaService
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

@Path("/agencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AgenciaResource(@Inject var agenciaService: AgenciaService) {

    @POST
    @Path("/novo")
    fun create(
        @Valid form : AgenciaForm
    ) = agenciaService.create(form)

    @GET
    @Path("/all")
    fun listAll() = agenciaService.listAll()

    @GET
    @Path("/{id}")
    fun findById(
        @PathParam("id") id: Long
    ) = agenciaService.listById(id)

    @PUT
    @Path("/{id}")
    fun update(
        @PathParam("id") id: Long,
        @Valid form: AgenciaUpdateForm
    ) = agenciaService.update(id,form)

    @DELETE
    @Path("/{id}")
    fun deleteById(
        @PathParam("id") id: Long
    ) = agenciaService.delete(id)
}