package br.com.javaweb.projeto.entity;

import jakarta.persistence.*;
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
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "nome_usuario", length = 200, nullable = false)
    private String nome;

    @Getter @Setter
    @Column(name = "email_usuario", length = 50,nullable = false, unique = true)
    private String email;

    @Getter @Setter
    @Column(name = "senha_usuario", columnDefinition = "TEXT", nullable = false)
    private String senha;

    @Getter @Setter
    @Column(name = "telefone_usuario", length = 15, nullable = false, unique = true)
    private String telefone;

}
