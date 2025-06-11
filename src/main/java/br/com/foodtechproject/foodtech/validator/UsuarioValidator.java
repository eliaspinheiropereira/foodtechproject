package br.com.foodtechproject.foodtech.validator;

import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.entities.DonoRestaurante;
import br.com.foodtechproject.foodtech.entities.Usuario;
import br.com.foodtechproject.foodtech.exceptions.RegistroDuplicadoException;
import br.com.foodtechproject.foodtech.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {

    private final ClienteRepository clienteRepository;

    public void validar(Usuario usuario) {
        if (usuario instanceof Cliente) {
            var clienteExiste = this.clienteRepository.findByNomeAndEmail(usuario.getNome(), usuario.getEmail());
            if (clienteExiste.isPresent()) {
                throw new RegistroDuplicadoException("esse cliente ja foi cadastrado");
            }
        }else {
//            logica para o dono do restaurante
            throw new IllegalArgumentException("essa etapa e para construir a logica do dono do restaurante");
        }
    }
}
