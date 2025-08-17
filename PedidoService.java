package com.ecommerce.service; // ajuste se necessário

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

        // regra extra ilustrativa: impedir pedido sem itens (se sua entidade tiver a lista de itens)
        // if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
        //     throw new RegraNegocioException("Pedido precisa ter pelo menos um item.");
        // }

        return pedidoRepository.save(pedido);
    }
}
