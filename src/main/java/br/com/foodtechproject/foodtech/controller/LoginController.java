package br.com.foodtechproject.foodtech.controller;

import br.com.foodtechproject.foodtech.dto.ClienteDTO;
import br.com.foodtechproject.foodtech.dto.LoginDTO;
import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.entities.Login;
import br.com.foodtechproject.foodtech.exceptions.LoginNaoExisteException;
import br.com.foodtechproject.foodtech.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("{id}")
    public ResponseEntity<LoginDTO> pesquisarCliente(@PathVariable Long id){
        logger.info("GET -> /login/"+id);
        Optional<Login> loginOptional = this.loginService.pesquisarLoginPorId(id);

        if(loginOptional.isPresent()){
            var login = loginOptional.get();
            LoginDTO loginDTO = new LoginDTO(
                    login.getId(),
                    login.getLogin(),
                    login.getSenha()
            );
            return ResponseEntity.ok(loginDTO);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarLogin(@PathVariable("id") Long id, @RequestBody LoginDTO loginDTO) {
        logger.info("PUT -> /login/"+id);
        var login = loginDTO.mapearLogin();
        this.loginService.atualizarLogin(id, login);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(LoginNaoExisteException.class)
    public ResponseEntity<LoginNaoExisteException> exception(LoginNaoExisteException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
