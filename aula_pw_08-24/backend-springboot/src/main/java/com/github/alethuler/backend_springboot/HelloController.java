package com.github.alethuler.backend_springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class HelloController {

    Map<String, String> mensagemDeOla = Map.of("menssagem", "Oi Spring Boot");

    @GetMapping("/")
    public Map<String, String> hello(){
        return mensagemDeOla;
    }
}
