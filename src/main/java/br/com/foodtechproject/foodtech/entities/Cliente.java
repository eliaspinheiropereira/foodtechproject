package br.com.foodtechproject.foodtech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "cliente")
public class Cliente extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    public Cliente(String nome, String email, Login login, Endereco endereco) {
        super(nome, email, login, endereco);
        this.dataUltimaAtualizacao = LocalDateTime.now();
    }
}
