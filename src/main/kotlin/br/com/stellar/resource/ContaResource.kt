package br.com.stellar.resource

import br.com.stellar.form.CreateContaForm
import br.com.stellar.form.UpdateContaForm
import br.com.stellar.service.ContaService
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


@Path("/conta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ContaResource(@Inject var contaService: ContaService) {

    @POST
    @Path("/novo")
    @RolesAllowed("admin")
    fun create(
        @Valid form: CreateContaForm
    ) = contaService.create(form)

    @GET
    @Path("/all")
    @RolesAllowed("admin")
    fun listAll(): Response {
        return Response.ok(contaService.listAll()).build()
    }

    @GET
    @Path("/minhas")
    @RolesAllowed("user")
    fun listAllbyUsuarioId(): Response {
        return Response.ok(contaService.listAllByUsuarioId()).build()
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    fun findById(
        @PathParam("id") id: Long
    ) = contaService.listById(id)

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    fun update(
        @PathParam("id") id: Long,
        @Valid form: UpdateContaForm
    ) = contaService.update(id, form)


    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    fun delete(
        @PathParam("id") id: Long
    ) = contaService.delete(id)


    @GET
    @Path("/{number}")
    @RolesAllowed("user")
    fun listByIdNumber(
        @PathParam("number") number: String
    ): Response {
        return Response.ok(contaService.listByNumber(number)).build()
    }
}