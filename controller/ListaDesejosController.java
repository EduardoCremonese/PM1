package com.ecommerce.controller;

import com.ecommerce.model.ListaDesejos;
import com.ecommerce.repository.ListaDesejosRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas-desejos")
@RequiredArgsConstructor
public class ListaDesejosController {

    private final ListaDesejosRepository repository;

    @PostMapping
    public ListaDesejos criar(@Valid @RequestBody ListaDesejos lista) {
        return repository.save(lista);
    }

    @GetMapping
    public List<ListaDesejos> listar(@RequestParam(required = false) Long usuarioId) {
        if (usuarioId != null) {
            return repository.findByUsuarioId(usuarioId)
                    .map(List::of)
                    .orElse(List.of());
        }
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ListaDesejos buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Lista de desejos não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
