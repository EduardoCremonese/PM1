package com.ecommerce.service;

import com.ecommerce.model.Endereco;
import com.ecommerce.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;

    public Endereco salvar(Endereco endereco) {
        return repository.save(endereco);
    }

    public List<Endereco> listarTodos() {
        return repository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public Endereco atualizar(Long id, Endereco endereco) {
        Endereco existente = buscarPorId(id);
        existente.setLogradouro(endereco.getLogradouro());
        existente.setNumero(endereco.getNumero());
        existente.setBairro(endereco.getBairro());
        existente.setCidade(endereco.getCidade());
        existente.setEstado(endereco.getEstado());
        existente.setCep(endereco.getCep());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
