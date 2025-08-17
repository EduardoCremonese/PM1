package com.ecommerce.repository; // ajuste se necessário

import com.ecommerce.model.Pedido;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // ... seus finders existentes (findByUsuarioId, findByStatus, etc.)

    @Query("SELECT p FROM Pedido p JOIN p.usuario u " +
            "WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Pedido> findByNomeUsuarioLike(@Param("nome") String nome);
}
