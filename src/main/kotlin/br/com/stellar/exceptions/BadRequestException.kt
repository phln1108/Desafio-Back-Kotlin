package br.com.stellar.exceptions

class BadRequestException(override var message: String) : RuntimeException(message)
