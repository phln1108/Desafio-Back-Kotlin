package br.com.stellar.exceptions.mappers

import br.com.stellar.exceptions.SaldoInsuficienteException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Provider
class SaldoInsuficienteExceptionMapper : ExceptionMapper<SaldoInsuficienteException> {
    override fun toResponse(exception: SaldoInsuficienteException?): Response? {
        val errorResponse = buildJsonObject {
            put("error", exception?.message ?: "Saldo insuficiente.")
        }

        return Response.status(Response.Status.NOT_ACCEPTABLE)
            .entity(errorResponse)
            .build()
    }
}