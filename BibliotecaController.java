package br.com.bibliotecaabc.controller;

import br.com.bibliotecaabc.model.Biblioteca;
import br.com.bibliotecaabc.service.BibliotecaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bibliotecas")
public class BibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    @GetMapping
    public ResponseEntity<List<Biblioteca>> listarTodos() {
        return ResponseEntity.ok(bibliotecaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> buscarPorId(@PathVariable Integer id) {
        return bibliotecaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Biblioteca> criar(@RequestBody Biblioteca biblioteca) {
        return ResponseEntity.ok(bibliotecaService.cadastrar(biblioteca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> atualizar(@PathVariable Integer id, @RequestBody Biblioteca biblioteca) {
        Biblioteca atualizado = bibliotecaService.editar(id, biblioteca);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        bibliotecaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
