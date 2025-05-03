package br.com.stellar.exceptions.mappers

import br.com.stellar.exceptions.OperacaoNaoPermitidaException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Provider
class OperacaoNaoPermitidaExceptionMapper : ExceptionMapper<OperacaoNaoPermitidaException> {
    override fun toResponse(exception: OperacaoNaoPermitidaException?): Response? {
        val errorResponse = buildJsonObject {
            put("error", exception?.message ?: "Operação não permitida.")
        }

        return Response.status(Response.Status.UNAUTHORIZED)
            .entity(errorResponse)
            .build()
    }
}