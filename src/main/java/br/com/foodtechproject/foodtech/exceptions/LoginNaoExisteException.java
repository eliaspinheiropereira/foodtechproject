package br.com.foodtechproject.foodtech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoginNaoExisteException extends RuntimeException {

    public LoginNaoExisteException(String message) {
        super(message);
    }
}
