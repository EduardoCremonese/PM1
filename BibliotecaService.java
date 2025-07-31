package br.com.bibliotecaabc.service;

import br.com.bibliotecaabc.model.Biblioteca;
import br.com.bibliotecaabc.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepo;

    public List<Biblioteca> listarTodos() {
        return bibliotecaRepo.buscarTodos();
    }

    public Optional<Biblioteca> buscarPorId(Integer id) {
        return bibliotecaRepo.buscarPorId(id);
    }

    public Biblioteca cadastrar(Biblioteca biblioteca) {
        return bibliotecaRepo.adicionar(biblioteca);
    }

    public void excluir(Integer id) {
        bibliotecaRepo.remover(id);
    }

    public Biblioteca editar(Integer id, Biblioteca bibliotecaAtualizada) {
        return bibliotecaRepo.atualizar(id, bibliotecaAtualizada);
    }
}
