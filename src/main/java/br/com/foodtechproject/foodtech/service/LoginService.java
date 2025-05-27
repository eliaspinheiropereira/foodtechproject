package br.com.foodtechproject.foodtech.service;

import br.com.foodtechproject.foodtech.entities.Login;
import br.com.foodtechproject.foodtech.exceptions.LoginNaoExisteException;
import br.com.foodtechproject.foodtech.repositories.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Optional<Login> pesquisarLoginPorId(Long id) {
        return this.loginRepository.findById(id);
    }

    public void atualizarLogin(Long id, Login login) {
        Optional<Login> loginOptional = this.loginRepository.findById(id);
        if (loginOptional.isPresent()) {
            Login loginAtualizado = loginOptional.get();

            if(login.getLogin() != null){
                loginAtualizado.setLogin(login.getLogin());
            }

            if(login.getSenha() != null){
                loginAtualizado.setSenha(login.getSenha());
            }

            this.loginRepository.save(loginAtualizado);
        }else{
            throw new LoginNaoExisteException("login não encontrado");
        }
    }
}
