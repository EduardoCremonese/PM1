package br.com.bibliotecaabc.controller;

import br.com.bibliotecaabc.model.Editora;
import br.com.bibliotecaabc.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoras")
public class EditoraController {

    @Autowired
    private EditoraService editoraService;

    @GetMapping
    public ResponseEntity<List<Editora>> listarTodas() {
        return ResponseEntity.ok(editoraService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editora> buscarPorId(@PathVariable Integer id) {
        return editoraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Editora> criar(@RequestBody Editora editora) {
        return ResponseEntity.ok(editoraService.cadastrar(editora));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editora> atualizar(@PathVariable Integer id, @RequestBody Editora editora) {
        Editora atualizada = editoraService.editar(id, editora);
        if (atualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        editoraService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
