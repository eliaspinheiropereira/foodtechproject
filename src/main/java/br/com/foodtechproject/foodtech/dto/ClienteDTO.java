package br.com.foodtechproject.foodtech.dto;

import br.com.foodtechproject.foodtech.entities.Endereco;
import br.com.foodtechproject.foodtech.entities.Login;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ClienteDTO(Long id, String nome, String email, @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataUltimaAtualizacao, Login login, Endereco endereco) {
}
