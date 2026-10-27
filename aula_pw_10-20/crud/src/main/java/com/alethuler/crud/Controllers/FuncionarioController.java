package com.alethuler.crud.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuncionarioController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String endpointVazio(){
        return "<h1>Bem vindo a minha API!<br>" +
                "Aqui você pode fazer os GETs, POSTs, DELETEs e UPDATEs a rodo!!!!</h1>";
    }
}
