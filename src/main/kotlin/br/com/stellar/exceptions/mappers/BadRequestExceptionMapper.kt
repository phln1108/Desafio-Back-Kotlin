package br.com.stellar.exceptions.mappers

import br.com.stellar.exceptions.BadRequestException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Provider
class BadRequestExceptionMapper : ExceptionMapper<BadRequestException> {
    override fun toResponse(exception: BadRequestException?): Response? {
        val errorResponse = buildJsonObject {
            put("error", exception?.message ?: "Ilegal argument.")
        }

        return Response.status(Response.Status.BAD_REQUEST)
            .entity(errorResponse) // Inclui a mensagem de erro no corpo da resposta
            .build()
    }
}