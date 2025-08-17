package com.ecommerce.controller;

import com.ecommerce.model.Produto;
import com.ecommerce.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public Produto criar(@Valid @RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @GetMapping
    public List<Produto> listar(@RequestParam(required = false) String nome,
                                @RequestParam(required = false) Double precoMin,
                                @RequestParam(required = false) Double precoMax,
                                @RequestParam(required = false) Integer estoqueMenorQue) {

        if (nome != null) {
            return service.buscarPorNome(nome);
        }
        if (precoMin != null && precoMax != null) {
            return service.buscarPorPreco(precoMin, precoMax);
        }
        if (estoqueMenorQue != null) {
            return service.produtosComEstoqueBaixo(estoqueMenorQue);
        }
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}
