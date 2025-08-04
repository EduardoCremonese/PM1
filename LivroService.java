package br.com.bibliotecaabc.service;

import br.com.bibliotecaabc.model.Livro;
import br.com.bibliotecaabc.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepo;

    public List<Livro> listarTodos() {
        return livroRepo.buscarTodos();
    }

    public Optional<Livro> buscarPorId(Integer id) {
        return livroRepo.buscarPorId(id);
    }

    public Livro cadastrar(Livro livro) {
        return livroRepo.adicionar(livro);
    }

    public void excluir(Integer id) {
        livroRepo.remover(id);
    }

    public Livro editar(Integer id, Livro livroAtualizado) {
        return livroRepo.atualizar(id, livroAtualizado);
    }
}
