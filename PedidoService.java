package com.ecommerce.service; 

import com.ecommerce.model.Pedido;
import com.ecommerce.repository.PedidoRepository;
import com.ecommerce.exception.RegraNegocioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public Pedido criar(Pedido pedido) {
        if (pedido.getUsuario() == null || pedido.getUsuario().getId() == null) {
            throw new RegraNegocioException("Pedido precisa estar associado a um usuário.");
        }

        if (pedido.getStatus() == null ||
                !List.of("ABERTO", "FECHADO", "CANCELADO").contains(pedido.getStatus())) {
            throw new RegraNegocioException("Status de pedido inválido. Use ABERTO, FECHADO ou CANCELADO.");
        }

    
        return pedidoRepository.save(pedido);
    }
}

