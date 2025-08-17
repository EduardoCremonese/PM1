package com.ecommerce.repository;

import com.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca por parte do nome
    List<Produto> findByNomeIgnoreCaseContaining(String nome);

    // Busca por faixa de preço
    List<Produto> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);

    // Consulta JPQL para estoque baixo
    @Query("SELECT p FROM Produto p WHERE p.estoque < :quantidade")
    List<Produto> produtosComEstoqueBaixo(Integer quantidade);
}
