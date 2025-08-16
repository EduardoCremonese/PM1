package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "O campo número é obrigatório")
    private String numero;

    @NotBlank(message = "O campo bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "O campo cidade é obrigatório")
    private String cidade;

    @NotBlank(message = "O campo estado é obrigatório")
    private String estado;

    @NotBlank(message = "O campo cep é obrigatório")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("enderecos")
    @NotNull(message = "O usuário é obrigatório")
    private Usuario usuario;
}
