package com.ecommerce.repository;

import com.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeIgnoreCaseContaining(String nome);

    List<Produto> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);

    @Query("SELECT p FROM Produto p WHERE p.estoque < :quantidade")
    List<Produto> produtosComEstoqueBaixo(Integer quantidade);
}

