package br.com.foodtechproject.foodtech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class Usuario {
    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_login", referencedColumnName = "id")
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    public Usuario(String nome, String email, Login login, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.endereco = endereco;
    }
}
