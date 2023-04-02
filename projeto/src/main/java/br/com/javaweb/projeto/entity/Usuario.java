package br.com.javaweb.projeto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuarios")
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id_usuario", unique = true)
    private Long id;

    @Getter @Setter
    @NotBlank(message = "O nome é obrigatorio!")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres!")
    @Column(name = "nome_usuario", length = 200, nullable = false)
    private String nome;

    @Getter @Setter
    @Email(message = "Insira um email valido")
    @NotBlank(message = "O email é obrigatorio!")
    @Column(name = "email_usuario", length = 50,nullable = false, unique = true)
    private String email;

    @Getter @Setter
    @NotBlank(message = "A senha é obrigatoria!")
    @Column(name = "senha_usuario", columnDefinition = "TEXT", nullable = false)
    private String senha;

    @Getter @Setter
    @NotBlank(message = "O telefone é obrigatorio!")
    @Column(name = "telefone_usuario", length = 15, nullable = false, unique = true)
    private String telefone;

}
