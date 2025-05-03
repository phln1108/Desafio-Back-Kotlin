package br.com.stellar.resource

import br.com.stellar.form.CreateBancoForm
import br.com.stellar.form.UpdateBancoForm
import br.com.stellar.service.BancoService
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

@Path("/banco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BancoResource(@Inject var bancoService: BancoService) {

    @POST
    @Path("/novo")
    @RolesAllowed("admin")
    fun create(
        @Valid form: CreateBancoForm
    ) = bancoService.criarBanco(form)

    @GET
    @Path("/all")
    @RolesAllowed("admin")
    fun findAll() = bancoService.listBancos();

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    fun findById(
        @PathParam("id") id: Long
    ) = bancoService.listById(id)

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    fun update(
        @PathParam("id") id: Long,
        @Valid form: UpdateBancoForm
    ) = bancoService.updateBanco(id,form)

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    fun deleteById(
        @PathParam("id") id: Long
    ) = bancoService.delete(id)

}