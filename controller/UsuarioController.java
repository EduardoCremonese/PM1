package com.ecommerce.controller;

import com.ecommerce.model.Usuario;
import com.ecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return service.listarTodos();
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/nome")
    public Usuario buscarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/email")
    public Usuario buscarPorEmail(@RequestParam String email) {
        return service.buscarPorEmail(email);
    }
}
