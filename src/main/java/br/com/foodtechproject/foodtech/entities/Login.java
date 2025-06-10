package br.com.foodtechproject.foodtech.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "login")
    private String userName;

    @Column(name = "senha")
    private String senha;

    public Login(){}

    public Login(String userName, String senha) {
        this.userName = userName;
        this.senha = senha;
    }
}
