package com.ecommerce.repository;

import com.ecommerce.model.ListaDesejos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListaDesejosRepository extends JpaRepository<ListaDesejos, Long> {

    boolean existsByUsuarioId(Long usuarioId);

    // Novo método para buscar pelo ID do usuário
    Optional<ListaDesejos> findByUsuarioId(Long usuarioId);
}
