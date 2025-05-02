package br.com.stellar.exceptions

class SaldoInsuficienteException(override var message: String) : RuntimeException(message)
