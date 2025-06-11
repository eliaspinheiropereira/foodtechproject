package br.com.foodtechproject.foodtech.dto;

import br.com.foodtechproject.foodtech.entities.Login;

public record LoginDTO(String userName, String senha) {

    public Login mapearLogin(){
        return new Login(userName, senha);
    }
}
