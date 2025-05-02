package br.com.stellar.resource

import br.com.stellar.form.CreateUsuarioForm
import br.com.stellar.form.UpdateUsuarioForm
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


@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UsuarioResource(@Inject var usuarioService: UsuarioService) {

    @POST
    @Path("/novo")
    fun create(
        @Valid form: CreateUsuarioForm
    ) = usuarioService.create(form)

    @GET
    @Path("/all")
    fun listAll() = usuarioService.listAll()

    @GET
    @Path("/{id}")
    fun listById(
        @PathParam("id") id: Long
    ) = usuarioService.listById(id)

    @PUT
    @Path("/{id}")
    fun update(
        @PathParam("id") id: Long,
        @Valid form: UpdateUsuarioForm
    ) = usuarioService.update(id, form)

    @DELETE
    @Path("/{id}")
    fun delete(
        @PathParam("id") id: Long
    ) = usuarioService.delete(id)

}