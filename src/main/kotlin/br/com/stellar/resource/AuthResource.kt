package br.com.stellar.resource

import br.com.stellar.entities.Usuario
import br.com.stellar.exceptions.BadRequestException
import br.com.stellar.exceptions.NotFoundException
import br.com.stellar.form.CreateUsuarioForm
import br.com.stellar.form.LoginForm
import br.com.stellar.model.TokenDTO
import br.com.stellar.service.ContaService
import br.com.stellar.service.UsuarioService
import io.quarkus.elytron.security.common.BcryptUtil
import io.smallrye.jwt.build.Jwt
import jakarta.annotation.security.PermitAll
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.jwt.JsonWebToken
import java.time.Duration


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AuthResource(@Inject var usuarioService: UsuarioService) {

    @POST
    @Path("/register")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    fun register(form: CreateUsuarioForm) = Response.ok(usuarioService.create(form)).status(201).build()


    @POST
    @Path("/login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    fun login(loginRequest: LoginForm): Response {
        val usuario = Usuario.find("email", loginRequest.email).firstResult()
            ?: throw NotFoundException("Usuário não encontrado")

        if (!BcryptUtil.matches(loginRequest.senha, usuario.senha)) {
            throw BadRequestException("Senha incorreta")
        }

        val roles = mutableSetOf("user").apply {
            if (usuario.isAdmin) add("admin")
        }

        val token = Jwt.issuer("stellar-bank")
            .upn(usuario.email)
            .groups(roles)
            .claim("id", usuario.id)
            .expiresIn(Duration.ofHours(1))
            .sign()

        return Response.ok(TokenDTO(token)).build()
    }
}