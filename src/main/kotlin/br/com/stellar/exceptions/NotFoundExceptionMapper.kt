package br.com.stellar.exceptions

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Provider
class NotFoundExceptionMapper : ExceptionMapper<NotFoundException> {
    override fun toResponse(exception: NotFoundException?): Response? {
        val errorResponse = buildJsonObject {
            put("error", exception?.message ?: "NotFound 404")
        }

        return Response.status(Response.Status.NOT_FOUND)
            .entity(errorResponse) // Inclui a mensagem de erro no corpo da resposta
            .build()
    }
}
