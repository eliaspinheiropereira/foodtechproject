package br.com.foodtechproject.foodtech.dto;

import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.entities.Endereco;

public record UsuarioDTO(String nome, String email, LoginDTO login, EnderecoDTO endereco) {

    public Cliente mapearCliente(){
        Cliente cliente = new Cliente(nome, email);
        cliente.setLogin(login.mapearLogin());
        cliente.setEndereco(endereco.mapearEndereco());

        return cliente;
    }
}
