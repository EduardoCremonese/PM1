package br.com.bibliotecaabc.service;

import br.com.bibliotecaabc.model.Autor;
import br.com.bibliotecaabc.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepo;

    public List<Autor> listarTodos() {
        return autorRepo.buscarTodos();
    }

    public Optional<Autor> buscarPorId(Integer id) {
        return autorRepo.buscarPorId(id);
    }

    public Autor cadastrar(Autor autor) {
        return autorRepo.adicionar(autor);
    }

    public void excluir(Integer id) {
        autorRepo.remover(id);
    }

    public Autor editar(Integer id, Autor autorAtualizado) {
        return autorRepo.atualizar(id, autorAtualizado);
    }
}
