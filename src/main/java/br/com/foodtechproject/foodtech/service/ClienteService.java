package br.com.foodtechproject.foodtech.service;

import br.com.foodtechproject.foodtech.dto.UsuarioDTO;
import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public void salvarCliente(UsuarioDTO usuarioDTO){
        Cliente novoCliente = usuarioDTO.mapearCliente();
        this.clienteRepository.save(novoCliente);
    }
}
