package br.com.foodtechproject.foodtech.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name = "donoRestaurante")
public class DonoRestaurante extends Usuario{

    public DonoRestaurante() {
        this.setDataUltimaAtualizacao(LocalDateTime.now());
    }

    public DonoRestaurante(String nome, String email) {
        super(nome, email);
        this.setDataUltimaAtualizacao(LocalDateTime.now());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.getNome()+", "+this.getEmail()+", "+formatter.format(this.getDataUltimaAtualizacao())+", "
                +this.getLogin().getLogin()+", "+this.getLogin().getSenha()+", "
                +this.getEndereco().getLogradouro()+", "+this.getEndereco().getBairro()+", "+this.getEndereco().getCidade()+", "
                +this.getEndereco().getEstado()+", "+this.getEndereco().getCep()+", "+this.getEndereco().getNumero();
    }
}
