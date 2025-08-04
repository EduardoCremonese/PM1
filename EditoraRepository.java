package br.com.bibliotecaabc.repository;

import br.com.bibliotecaabc.model.Editora;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EditoraRepository {

    private final List<Editora> editoras = new ArrayList<>();
    private int proximoId = 1;

    public List<Editora> buscarTodas() {
        return editoras;
    }

    public Optional<Editora> buscarPorId(Integer id) {
        return editoras.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public Editora adicionar(Editora editora) {
        editora.setId(proximoId++);
        editoras.add(editora);
        return editora;
    }

    public void remover(Integer id) {
        editoras.removeIf(e -> e.getId().equals(id));
    }

    public Editora atualizar(Integer id, Editora editoraAtualizada) {
        for (int i = 0; i < editoras.size(); i++) {
            if (editoras.get(i).getId().equals(id)) {
                editoraAtualizada.setId(id);
                editoras.set(i, editoraAtualizada);
                return editoraAtualizada;
            }
        }
        return null;
    }
}
