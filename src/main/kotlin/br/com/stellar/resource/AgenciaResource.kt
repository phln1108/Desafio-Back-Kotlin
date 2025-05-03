package br.com.stellar.resource

import br.com.stellar.form.CreateAgenciaForm
import br.com.stellar.form.UpdateAgenciaForm
import br.com.stellar.service.AgenciaService
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

@Path("/agencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AgenciaResource(@Inject var agenciaService: AgenciaService) {

    @POST
    @Path("/novo")
    @RolesAllowed("admin")
    fun create(
        @Valid form : CreateAgenciaForm
    ) = agenciaService.create(form)

    @GET
    @Path("/all")
    @RolesAllowed("admin")
    fun listAll() = agenciaService.listAll()

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    fun findById(
        @PathParam("id") id: Long
    ) = agenciaService.listById(id)

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    fun update(
        @PathParam("id") id: Long,
        @Valid form: UpdateAgenciaForm
    ) = agenciaService.update(id,form)

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    fun deleteById(
        @PathParam("id") id: Long
    ) = agenciaService.delete(id)
}