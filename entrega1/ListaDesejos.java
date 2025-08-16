package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ListaDesejos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da lista de desejos é obrigatório")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("listasDesejo")
    @NotNull(message = "O usuário é obrigatório")
    private Usuario usuario;

    @NotEmpty(message = "A lista de produtos não pode estar vazia")
    @ManyToMany
    @JoinTable(
            name = "lista_desejos_produtos",
            joinColumns = @JoinColumn(name = "lista_desejos_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    @JsonIgnoreProperties("listasDesejo")
    private List<Produto> produtos;
}
