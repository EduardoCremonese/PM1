package com.ecommerce.controller;

import com.ecommerce.model.Endereco;
import com.ecommerce.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor 
public class EnderecoController {

    private final EnderecoService service;

    @PostMapping
    public Endereco criar(@RequestBody Endereco endereco) {
        return service.salvar(endereco);
    }

    @GetMapping
    public List<Endereco> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Endereco buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Endereco atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
        return service.atualizar(id, endereco);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}

