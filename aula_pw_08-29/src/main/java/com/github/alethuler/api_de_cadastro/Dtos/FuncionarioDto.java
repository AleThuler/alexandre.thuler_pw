package com.github.alethuler.api_de_cadastro.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDto {


    private String nome;

    private int salario;

    private String cargo;
}
