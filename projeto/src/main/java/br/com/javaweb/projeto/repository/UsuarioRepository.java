package br.com.javaweb.projeto.repository;

import br.com.javaweb.projeto.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
