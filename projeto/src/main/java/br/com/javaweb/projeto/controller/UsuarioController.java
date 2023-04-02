package br.com.javaweb.projeto.controller;

import br.com.javaweb.projeto.entity.Usuario;
import br.com.javaweb.projeto.repository.UsuarioRepository;
import br.com.javaweb.projeto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {


    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos(){
        return ResponseEntity.status(200).body(usuarioService.listarUsuarios());
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionar(@Valid @RequestBody Usuario usuario){
        return ResponseEntity.status(201).body(usuarioService.salvar(usuario));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> editar(@Valid @PathVariable Long id, @RequestBody Usuario entidade){
        entidade = usuarioService.editar(id,entidade);
        return ResponseEntity.status(200).body(entidade);
    }

    @DeleteMapping(value =  "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> validarSenha(@Valid @RequestBody Usuario usuario){
        Boolean valido = usuarioService.validarSenha(usuario);
        if(!valido){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(200).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }
}
