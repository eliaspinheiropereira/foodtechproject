package br.com.foodtechproject.foodtech.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "cliente")
public class Cliente extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    public Cliente(){
        this.dataUltimaAtualizacao = LocalDateTime.now();
    }

    public Cliente(String nome, String email, Login login, Endereco endereco) {
        super(nome, email, login, endereco);
        this.dataUltimaAtualizacao = LocalDateTime.now();
    }

}
