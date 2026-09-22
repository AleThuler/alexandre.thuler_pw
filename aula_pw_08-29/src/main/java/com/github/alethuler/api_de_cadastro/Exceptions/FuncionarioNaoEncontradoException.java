package com.github.alethuler.api_de_cadastro.Exceptions;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    final String MENSAGEM_DE_ERRO = "Funcionario não encontrado";

    public FuncionarioNaoEncontradoException(String message) {
        super(message);
    }
}
