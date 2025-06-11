package br.com.foodtechproject.foodtech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsuarioNaoExistenteException extends RuntimeException {
    public UsuarioNaoExistenteException(String message) {
        super(message);
    }
}
