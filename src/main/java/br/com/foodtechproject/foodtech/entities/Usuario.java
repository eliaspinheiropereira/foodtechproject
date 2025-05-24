package br.com.foodtechproject.foodtech.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class Usuario {
    private String nome;
    private String email;
    private Login login;
    private Endereco endereco;

    public Usuario(String nome, String email, Login login, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.endereco = endereco;
    }
}
