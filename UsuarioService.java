package com.ecommerce.service;

import com.ecommerce.exception.RegraNegocioException;
import com.ecommerce.exception.RecursoNaoEncontradoException;
import com.ecommerce.model.Usuario;
import com.ecommerce.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Transactional
    public Usuario salvar(Usuario usuario) {
        aplicarRegraStatusCadastro(usuario);
        validarEmailDuplicado(usuario);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dto) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado para atualização"));

        existente.setNome(dto.getNome());
        existente.setEmail(dto.getEmail());
        existente.setTelefone(dto.getTelefone());

        aplicarRegraStatusCadastro(existente);
        validarEmailDuplicado(existente);

        return usuarioRepository.save(existente);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado para exclusão");
        }
        usuarioRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public Usuario buscarPorNome(String nome) {
        return usuarioRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com esse nome"));
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com esse e-mail"));
    }


    private void aplicarRegraStatusCadastro(Usuario usuario) {
        if (usuario.getTelefone() == null || usuario.getTelefone().isBlank()) {
            usuario.setStatusCadastro("INCOMPLETO");
        } else {
            usuario.setStatusCadastro("COMPLETO");
        }
    }

    private void validarEmailDuplicado(Usuario usuario) {
        usuarioRepository.findByEmailIgnoreCase(usuario.getEmail())
                .filter(existing -> !existing.getId().equals(usuario.getId()))
                .ifPresent(existing -> {
                    throw new RegraNegocioException("E-mail já cadastrado para outro usuário");
                });
    }
}

