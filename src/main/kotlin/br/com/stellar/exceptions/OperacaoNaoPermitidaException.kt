package br.com.stellar.exceptions

class OperacaoNaoPermitidaException(override var message: String) : RuntimeException(message)
