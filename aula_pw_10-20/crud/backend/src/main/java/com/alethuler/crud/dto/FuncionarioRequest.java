package com.etec.alethuler.dto;

import java.math.BigDecimal;

public record FuncionarioRequest(String nome, String funcao, BigDecimal salario) {
}
