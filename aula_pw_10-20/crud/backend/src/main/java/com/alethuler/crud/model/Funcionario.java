package com.etec.alethuler.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Funcionario(
        Long id,
        String nome,
        String funcao,
        BigDecimal salario,
        LocalDateTime createdAt) {
}
