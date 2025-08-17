package com.ecommerce.repository;

import com.ecommerce.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByCidadeIgnoreCase(String cidade);
    List<Endereco> findByEstadoIgnoreCase(String estado);
}
