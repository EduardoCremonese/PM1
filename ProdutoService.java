package com.ecommerce.service;

import com.ecommerce.exception.RecursoNaoEncontradoException;
import com.ecommerce.model.Produto;
import com.ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, Produto dto) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado para atualização"));

        existente.setNome(dto.getNome());
        existente.setPreco(dto.getPreco());
        existente.setEstoque(dto.getEstoque());

        return produtoRepository.save(existente);
    }

    @Transactional(readOnly = true)
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));
    }

    @Transactional
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado para exclusão");
        }
        produtoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeIgnoreCaseContaining(nome);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorPreco(Double precoMin, Double precoMax) {
        BigDecimal min = BigDecimal.valueOf(precoMin);
        BigDecimal max = BigDecimal.valueOf(precoMax);
        return produtoRepository.findByPrecoBetween(min, max);
    }

    @Transactional(readOnly = true)
    public List<Produto> produtosComEstoqueBaixo(Integer quantidade) {
        return produtoRepository.produtosComEstoqueBaixo(quantidade);
    }

    @Transactional
    public void excluir(Long id) {
        deletar(id); 
    }
}

