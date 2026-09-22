package com.github.alethuler.api_de_cadastro.Database.Repositorys;

import com.github.alethuler.api_de_cadastro.Database.Entitys.FuncionarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FuncionarioRepository extends MongoRepository<FuncionarioEntity, String> {
    Optional<FuncionarioEntity> findByNome(String nome);
}
