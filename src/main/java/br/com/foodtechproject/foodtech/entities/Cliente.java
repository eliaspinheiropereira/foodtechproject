package br.com.foodtechproject.foodtech.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Cliente extends Usuario{

    private LocalDateTime dataUltimaAtualizacao;

    public Cliente(String nome, String email, Login login, Endereco endereco) {
        super(nome, email, login, endereco);
        this.dataUltimaAtualizacao = LocalDateTime.now();
    }
}
