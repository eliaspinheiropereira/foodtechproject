package br.com.foodtechproject.foodtech.dto;

import br.com.foodtechproject.foodtech.entities.Endereco;

public record EnderecoDTO(String logradouro, String bairro, String cidade, String estado, String cep, String numero) {

    public Endereco mapearEndereco() {
        return new Endereco(logradouro, bairro, cidade, estado, cep, numero);
    }
}
