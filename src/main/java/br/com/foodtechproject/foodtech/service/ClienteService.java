package br.com.foodtechproject.foodtech.service;

import br.com.foodtechproject.foodtech.dto.ClienteDTO;
import br.com.foodtechproject.foodtech.dto.UsuarioDTO;
import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.entities.Usuario;
import br.com.foodtechproject.foodtech.exceptions.UsuarioExistenteException;
import br.com.foodtechproject.foodtech.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Page<Cliente> buscarClientes(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return clienteRepository.findAll(pageable);
    }

    public ClienteDTO buscarClientePorId(Long id){
        Cliente cliente = clienteRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("cliente não encontado"));

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

        Optional<Cliente> existeEmailDeCliente = this.clienteRepository.findByEmail(cliente.getEmail());

        if(existeEmailDeCliente.isPresent()){
            throw new UsuarioExistenteException("Esse cliente já foi cadastrado.");
        }else {
            this.clienteRepository.save(cliente);
        }
    }

    public void excluirCliente(Long id){
        this.clienteRepository.deleteById(id);
    }
}
