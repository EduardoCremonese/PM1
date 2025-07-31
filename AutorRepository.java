package br.com.bibliotecaabc.repository;

import br.com.bibliotecaabc.model.Autor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AutorRepository {

    private final List<Autor> autores = new ArrayList<>();
    private int proximoId = 1;

    public List<Autor> buscarTodos() {
        return autores;
    }

    public Optional<Autor> buscarPorId(Integer id) {
        return autores.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public Autor adicionar(Autor autor) {
        autor.setId(proximoId++);
        autores.add(autor);
        return autor;
    }

    public void remover(Integer id) {
        autores.removeIf(a -> a.getId().equals(id));
    }

    public Autor atualizar(Integer id, Autor autorAtualizado) {
        for (int i = 0; i < autores.size(); i++) {
            if (autores.get(i).getId().equals(id)) {
                autorAtualizado.setId(id);
                autores.set(i, autorAtualizado);
                return autorAtualizado;
            }
        }
        return null;
    }
}
