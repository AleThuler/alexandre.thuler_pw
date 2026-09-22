package com.github.alethuler.api_de_cadastro.Services;

import com.github.alethuler.api_de_cadastro.Database.Entitys.FuncionarioEntity;
import com.github.alethuler.api_de_cadastro.Database.Repositorys.FuncionarioRepository;
import com.github.alethuler.api_de_cadastro.Dtos.FuncionarioDto;
import com.github.alethuler.api_de_cadastro.Exceptions.FuncionarioNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioEntity> listarTodos(){
        return funcionarioRepository.findAll();
    }

    public FuncionarioEntity procurarPorNome(String nome){
        FuncionarioEntity funcionarioEncontrado = funcionarioRepository.findByNome(nome).orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionario não encontrado"));

        return funcionarioEncontrado;
    }

    public FuncionarioEntity criarUmFuncionario(FuncionarioDto funcionarioDto){
        FuncionarioEntity novoFuncionario = new FuncionarioEntity();

        novoFuncionario.setNome(funcionarioDto.getNome());
        novoFuncionario.setSalario(funcionarioDto.getSalario());
        novoFuncionario.setCargo(funcionarioDto.getCargo());

        return funcionarioRepository.save(novoFuncionario);
    }

    public FuncionarioEntity atualizarUmFuncionarioPeloNome(String nome, FuncionarioDto funcionarioDto){
        FuncionarioEntity funcionarioAtualizado = procurarPorNome(nome);

        funcionarioAtualizado.setNome(funcionarioDto.getNome());
        funcionarioAtualizado.setSalario(funcionarioDto.getSalario());
        funcionarioAtualizado.setCargo(funcionarioDto.getCargo());

        return funcionarioRepository.save(funcionarioAtualizado);
    }

    public String deletarUmFuncionarioPeloNome(String nome){
        FuncionarioEntity funcionarioASerDeletado = procurarPorNome(nome);

        funcionarioRepository.deleteById(funcionarioASerDeletado.getId());

        return "Funcionario deletado com sucesso!";
    }
}
