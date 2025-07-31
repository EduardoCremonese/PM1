package br.com.bibliotecaabc.repository;

import br.com.bibliotecaabc.model.Biblioteca;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BibliotecaRepository {

    private final List<Biblioteca> dados = new ArrayList<>();
    private int proximoId = 1;

    public List<Biblioteca> buscarTodos() {
        return dados;
    }

    public Optional<Biblioteca> buscarPorId(Integer id) {
        return dados.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    public Biblioteca adicionar(Biblioteca novaBiblioteca) {
        novaBiblioteca.setId(proximoId++);
        dados.add(novaBiblioteca);
        return novaBiblioteca;
    }

    public void remover(Integer id) {
        dados.removeIf(b -> b.getId().equals(id));
    }

    public Biblioteca atualizar(Integer id, Biblioteca bibliotecaAtualizada) {
        for (int i = 0; i < dados.size(); i++) {
            if (dados.get(i).getId().equals(id)) {
                bibliotecaAtualizada.setId(id);
                dados.set(i, bibliotecaAtualizada);
                return bibliotecaAtualizada;
            }
        }
        return null;
    }
}
