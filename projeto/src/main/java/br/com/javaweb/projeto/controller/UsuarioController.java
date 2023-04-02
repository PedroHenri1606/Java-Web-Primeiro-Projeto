package br.com.javaweb.projeto.controller;

import br.com.javaweb.projeto.entity.Usuario;
import br.com.javaweb.projeto.repository.UsuarioRepository;
import br.com.javaweb.projeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos(){
        List<Usuario> lista = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionar(@RequestBody Usuario usuario){
        usuario = usuarioService.salvar(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> editar(@PathVariable Long id, @RequestBody Usuario entidade){
        entidade = usuarioService.editar(id,entidade);
        return ResponseEntity.ok().body(entidade);
    }

    @DeleteMapping(value =  "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.status(204).build();
    }
}
