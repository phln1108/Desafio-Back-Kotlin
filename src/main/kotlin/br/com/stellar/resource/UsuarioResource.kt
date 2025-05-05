package br.com.stellar.resource

import br.com.stellar.form.CreateUsuarioForm
import br.com.stellar.form.UpdateUsuarioForm
import br.com.stellar.service.UsuarioService
import jakarta.annotation.security.RolesAllowed
import jakarta.inject.Inject
import jakarta.json.JsonNumber
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
import org.eclipse.microprofile.jwt.JsonWebToken


@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UsuarioResource(
    @Inject var usuarioService: UsuarioService,
    @Inject var jwt: JsonWebToken
    ) {

    @GET
    @RolesAllowed("user")
    fun listSelf() = usuarioService.listById(jwt.getClaim<JsonNumber>("id").longValue()
    )

    @GET
    @Path("/all")
    @RolesAllowed("admin")
    fun listAll() = usuarioService.listAll()

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    fun listById(
        @PathParam("id") id: Long
    ) = usuarioService.listById(id)

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    fun update(
        @PathParam("id") id: Long,
        @Valid form: UpdateUsuarioForm
    ) = usuarioService.update(id, form)

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    fun delete(
        @PathParam("id") id: Long
    ) = usuarioService.delete(id)

}