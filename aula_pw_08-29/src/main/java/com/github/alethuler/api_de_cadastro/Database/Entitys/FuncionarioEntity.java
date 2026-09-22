package com.github.alethuler.api_de_cadastro.Database.Entitys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "funcionario_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nome;

    private int salario;

    private String cargo;
}
