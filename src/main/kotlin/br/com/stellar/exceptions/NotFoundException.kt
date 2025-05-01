package br.com.stellar.exceptions

class NotFoundException(override var message: String) : RuntimeException(message)
