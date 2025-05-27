package br.com.foodtechproject.foodtech.dto;

import br.com.foodtechproject.foodtech.entities.Login;

public record LoginDTO(Long id, String login, String senha) {

    public Login mapearLogin(){
        return new Login(login, senha);
    }
}
