package com.ecommerce.controller; // ajuste se necessário

import com.ecommerce.model.Pedido;
import com.ecommerce.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // ... seus endpoints CRUD existentes

    // GET /pedidos/por-nome-usuario?nome=joao
    @GetMapping("/por-nome-usuario")
    public List<Pedido> buscarPorNomeUsuario(@RequestParam String nome) {
        return pedidoRepository.findByNomeUsuarioLike(nome);
    }
}
