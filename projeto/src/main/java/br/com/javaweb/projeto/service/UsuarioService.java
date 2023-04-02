package br.com.javaweb.projeto.service;

import br.com.javaweb.projeto.entity.Usuario;
import br.com.javaweb.projeto.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarUsuarios(){
        return repository.findAll();
    }

    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario editar(Long id, Usuario usuario){
        try{
            Usuario entidade = repository.getReferenceById(id);
            editarItens(entidade,usuario);
            return repository.save(entidade);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e);
        }
    }

    public void editarItens(Usuario entidade, Usuario usuario){
        entidade.setNome(usuario.getNome());
        entidade.setSenha(usuario.getSenha());
        entidade.setEmail(usuario.getEmail());
        entidade.setTelefone(usuario.getTelefone());
    }

   public void deletarUsuario(Long id){
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(e);
        }
    }
}


