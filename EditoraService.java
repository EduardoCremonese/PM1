package br.com.bibliotecaabc.service;

import br.com.bibliotecaabc.model.Editora;
import br.com.bibliotecaabc.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepo;

    public List<Editora> listarTodas() {
        return editoraRepo.buscarTodas();
    }

    public Optional<Editora> buscarPorId(Integer id) {
        return editoraRepo.buscarPorId(id);
    }

    public Editora cadastrar(Editora editora) {
        return editoraRepo.adicionar(editora);
    }

    public void excluir(Integer id) {
        editoraRepo.remover(id);
    }

    public Editora editar(Integer id, Editora editoraAtualizada) {
        return editoraRepo.atualizar(id, editoraAtualizada);
    }
}
