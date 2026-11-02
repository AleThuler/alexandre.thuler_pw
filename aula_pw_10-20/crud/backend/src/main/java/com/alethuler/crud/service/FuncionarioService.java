package com.etec.alethuler.service;

import com.etec.alethuler.dto.FuncionarioRequest;
import com.etec.alethuler.exception.DadosInvalidosException;
import com.etec.alethuler.exception.FuncionarioNaoEncontradoException;
import com.etec.alethuler.model.Funcionario;
import com.etec.alethuler.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuncionarioService {

    private static final BigDecimal SALARIO_MAXIMO = new BigDecimal("99999999.99");

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> listar() {
        return repository.listarTodos();
    }

    public Funcionario buscar(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(id));
    }

    public Funcionario criar(FuncionarioRequest dados) {
        validar(dados);
        return repository.inserir(dados);
    }

    public Funcionario atualizar(Long id, FuncionarioRequest dados) {
        validar(dados);

        if (repository.atualizar(id, dados) == 0) {
            throw new FuncionarioNaoEncontradoException(id);
        }
        return buscar(id);
    }

    public void remover(Long id) {
        if (repository.deletar(id) == 0) {
            throw new FuncionarioNaoEncontradoException(id);
        }
    }

    private void validar(FuncionarioRequest dados) {
        if (dados == null || vazio(dados.nome()) || vazio(dados.funcao()) || dados.salario() == null) {
            throw new DadosInvalidosException("Campos obrigatórios: nome, funcao e salario");
        }
        if (dados.nome().trim().length() > 100) {
            throw new DadosInvalidosException("O nome pode ter no máximo 100 caracteres");
        }
        if (dados.funcao().trim().length() > 100) {
            throw new DadosInvalidosException("A função pode ter no máximo 100 caracteres");
        }
        if (dados.salario().signum() <= 0) {
            throw new DadosInvalidosException("O salário deve ser maior que zero");
        }
        if (dados.salario().compareTo(SALARIO_MAXIMO) > 0) {
            throw new DadosInvalidosException("O salário é maior que o valor máximo permitido");
        }
    }

    private boolean vazio(String texto) {
        return texto == null || texto.isBlank();
    }
}
