package br.com.foodtechproject.foodtech.service;

import br.com.foodtechproject.foodtech.dto.ClienteDTO;
import br.com.foodtechproject.foodtech.dto.UsuarioDTO;
import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.entities.Endereco;
import br.com.foodtechproject.foodtech.entities.Login;
import br.com.foodtechproject.foodtech.exceptions.UsuarioNaoExistenteException;
import br.com.foodtechproject.foodtech.repositories.ClienteRepository;
import br.com.foodtechproject.foodtech.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioValidator usuarioValidator;

    public Page<Cliente> buscarClientes(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return clienteRepository.findAll(pageable);
    }

    public ClienteDTO buscarClientePorId(Long id){
        Cliente cliente = clienteRepository.findById(id).
                orElseThrow(() -> new UsuarioNaoExistenteException("usuario não cadastrado"));

        if(cliente != null) {
            ClienteDTO clienteDTO = new ClienteDTO(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getEmail(),
                    cliente.getDataUltimaAtualizacao(),
                    cliente.getLogin(),
                    cliente.getEndereco()
            );
            return clienteDTO;
        }
        return null;
    }

    public void salvarCliente(UsuarioDTO usuarioDTO){
        Cliente cliente = usuarioDTO.mapearCliente();
        this.usuarioValidator.validar(cliente);
        this.clienteRepository.save(cliente);
    }

    public void excluirCliente(Long id){
        var cliente = this.buscarClientePorId(id);
        this.clienteRepository.deleteById(cliente.id());
    }

    public void atualizarCliente(Long id, UsuarioDTO usuarioDTO) {
        Cliente clienteExistente = this.clienteRepository.findById(id).orElseThrow(() -> new UsuarioNaoExistenteException("usuario não cadastrado"));

        Cliente clienteAtualizado = usuarioDTO.mapearCliente();
//        Login loginAtualizado = usuarioDTO.mapearCliente().getLogin();

        Login loginExistente = clienteExistente.getLogin();
        if(loginExistente == null){
            loginExistente = new Login();
            clienteExistente.setLogin(loginExistente);
        }

        Endereco enderecoExistente = clienteExistente.getEndereco();
        if(enderecoExistente == null){
            enderecoExistente = new Endereco();
            clienteExistente.setEndereco(enderecoExistente);
        }

        clienteAtualizado.setId(id);
        clienteAtualizado.getLogin().setId(loginExistente.getId());
        clienteAtualizado.getEndereco().setId(enderecoExistente.getId());

        this.clienteRepository.save(clienteAtualizado);
    }
}
