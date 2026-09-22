package com.github.alethuler.api_de_cadastro.Controllers;

import com.github.alethuler.api_de_cadastro.Database.Entitys.FuncionarioEntity;
import com.github.alethuler.api_de_cadastro.Dtos.FuncionarioDto;
import com.github.alethuler.api_de_cadastro.Services.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String mensagemDeBoasVindas(){
        return "Olá, qual método você vai fazer??";
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<FuncionarioEntity> listarTodos(){
        return funcionarioService.listarTodos();
    }

    @GetMapping("/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public FuncionarioEntity listarFuncionarioPorNome(@PathVariable String nome){
        return funcionarioService.procurarPorNome(nome);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioEntity criarUmFuncionario(@RequestBody FuncionarioDto funcionarioDto){
        FuncionarioEntity novoFuncionario = funcionarioService.criarUmFuncionario(funcionarioDto);

        return novoFuncionario;
    }

    @PutMapping("/{nome}")
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioEntity mudarDadosDeUmFuncionario(@PathVariable String nome, @RequestBody FuncionarioDto funcionarioDto){
        FuncionarioEntity funcionarioMudado = funcionarioService.atualizarUmFuncionarioPeloNome(nome, funcionarioDto);

        return funcionarioMudado;
    }


    @DeleteMapping("/{nome}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletarUmFuncionarioPeloNome(@PathVariable String nome){
        return funcionarioService.deletarUmFuncionarioPeloNome(nome);
    }
}