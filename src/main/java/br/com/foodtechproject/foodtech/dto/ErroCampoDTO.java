package br.com.foodtechproject.foodtech.dto;

public class ErroCampoDTO extends RuntimeException{
    public ErroCampoDTO(String message) {
        super(message);
    }
}

