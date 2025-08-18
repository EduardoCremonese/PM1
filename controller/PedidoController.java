package com.ecommerce.controller; 

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
    
    @GetMapping("/por-nome-usuario")
    public List<Pedido> buscarPorNomeUsuario(@RequestParam String nome) {
        return pedidoRepository.findByNomeUsuarioLike(nome);
    }
}

