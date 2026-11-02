package com.etec.alethuler.exception;

public class ErroBancoException extends RuntimeException {

    public ErroBancoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
