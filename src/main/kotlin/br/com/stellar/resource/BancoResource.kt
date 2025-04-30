package br.com.stellar.resource

import br.com.stellar.form.BancoForm
import br.com.stellar.service.BancoService
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/banco")
class BancoResource(@Inject var bancoService: BancoService) {

    @POST
    @Path("/novo")
    fun create(
        form:BancoForm
    ) = bancoService.criarBanco(form)

    @GET
    @Path("/all")
    fun all() = bancoService.listBancos();
}