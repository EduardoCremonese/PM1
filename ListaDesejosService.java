package com.ecommerce.service;

import com.ecommerce.exception.RegraNegocioException;
import com.ecommerce.exception.RecursoNaoEncontradoException;
import com.ecommerce.model.ListaDesejos;
import com.ecommerce.model.Produto;
import com.ecommerce.repository.ListaDesejosRepository;
import com.ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListaDesejosService {

    private final ListaDesejosRepository listaDesejosRepository;
    private final ProdutoRepository produtoRepository;

    public ListaDesejosService(ListaDesejosRepository listaDesejosRepository,
                               ProdutoRepository produtoRepository) {
        this.listaDesejosRepository = listaDesejosRepository;
        this.produtoRepository = produtoRepository;
    }

    // --------- CRUD BÁSICO ---------

    @Transactional
    public ListaDesejos criar(ListaDesejos lista) {
        // RN #2 (exigida): lançar exception em Service
        if (lista.getUsuario() == null || lista.getUsuario().getId() == null) {
            throw new RegraNegocioException("Lista de desejos precisa estar associada a um usuário.");
        }
        Long usuarioId = lista.getUsuario().getId();

        if (listaDesejosRepository.existsByUsuarioId(usuarioId)) {
            throw new RegraNegocioException("Usuário já possui uma lista de desejos.");
        }

        return listaDesejosRepository.save(lista);
    }

    @Transactional
    public ListaDesejos atualizar(Long id, ListaDesejos dto) {
        ListaDesejos existente = listaDesejosRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Lista de desejos não encontrada para atualização"));

        // Se desejar permitir troca do usuário dono da lista:
        if (dto.getUsuario() != null && dto.getUsuario().getId() != null) {
            Long novoUsuarioId = dto.getUsuario().getId();

            // Impede que o novo usuário fique com duas listas
            if (listaDesejosRepository.existsByUsuarioId(novoUsuarioId)
                    && !novoUsuarioId.equals(existente.getUsuario().getId())) {
                throw new RegraNegocioException("Usuário selecionado já possui uma lista de desejos.");
            }
            existente.setUsuario(dto.getUsuario());
        }

        // Atualização dos produtos, se enviado:
        if (dto.getProdutos() != null) {
            existente.setProdutos(dto.getProdutos());
        }

        return listaDesejosRepository.save(existente);
    }

    @Transactional(readOnly = true)
    public ListaDesejos buscarPorId(Long id) {
        return listaDesejosRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Lista de desejos não encontrada"));
    }

    @Transactional(readOnly = true)
    public ListaDesejos buscarPorUsuario(Long usuarioId) {
        return listaDesejosRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não possui lista de desejos"));
    }

    @Transactional(readOnly = true)
    public List<ListaDesejos> listarTodas() {
        return listaDesejosRepository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        if (!listaDesejosRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Lista de desejos não encontrada para exclusão");
        }
        listaDesejosRepository.deleteById(id);
    }

    // --------- OPERAÇÕES DE PRODUTOS NA LISTA ---------

    @Transactional
    public ListaDesejos adicionarProduto(Long listaId, Long produtoId) {
        ListaDesejos lista = buscarPorId(listaId);
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));

        // Evita duplicata
        boolean jaExiste = lista.getProdutos().stream()
                .anyMatch(p -> p.getId().equals(produto.getId()));
        if (!jaExiste) {
            lista.getProdutos().add(produto);
        }

        return listaDesejosRepository.save(lista);
    }

    @Transactional
    public ListaDesejos removerProduto(Long listaId, Long produtoId) {
        ListaDesejos lista = buscarPorId(listaId);

        boolean removido = lista.getProdutos().removeIf(p -> p.getId().equals(produtoId));
        if (!removido) {
            throw new RecursoNaoEncontradoException("Produto não está presente na lista de desejos");
        }

        return listaDesejosRepository.save(lista);
    }
}
