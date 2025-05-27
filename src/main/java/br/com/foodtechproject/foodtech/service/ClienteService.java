package br.com.foodtechproject.foodtech.service;

import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.exceptions.UsuarioExistenteException;
import br.com.foodtechproject.foodtech.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Page<Cliente> pesquisarClientes(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return clienteRepository.findAll(pageable);
    }

    public Optional<Cliente> pesquisarClientePorId(Long id){
        return clienteRepository.findById(id);
    }

    public void salvarCliente(Cliente cliente){
        Optional<Cliente> clienteOptional = this.clienteRepository.findByEmail(cliente.getEmail());

        if(clienteOptional.isPresent()){
            throw new UsuarioExistenteException("Esse cliente já foi cadastrado.");
        }else {
            this.clienteRepository.save(cliente);
        }
    }

    public void excluirCliente(Long id){
        this.clienteRepository.deleteById(id);
    }
}
