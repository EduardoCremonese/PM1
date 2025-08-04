package br.com.bibliotecaabc.repository;

import br.com.bibliotecaabc.model.Livro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LivroRepository {

    private final List<Livro> livros = new ArrayList<>();
    private int proximoId = 1;

    public List<Livro> buscarTodos() {
        return livros;
    }

    public Optional<Livro> buscarPorId(Integer id) {
        return livros.stream().filter(l -> l.getId().equals(id)).findFirst();
    }

    public Livro adicionar(Livro livro) {
        livro.setId(proximoId++);
        livros.add(livro);
        return livro;
    }

    public void remover(Integer id) {
        livros.removeIf(l -> l.getId().equals(id));
    }

    public Livro atualizar(Integer id, Livro livroAtualizado) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId().equals(id)) {
                livroAtualizado.setId(id);
                livros.set(i, livroAtualizado);
                return livroAtualizado;
            }
        }
        return null;
    }
}
