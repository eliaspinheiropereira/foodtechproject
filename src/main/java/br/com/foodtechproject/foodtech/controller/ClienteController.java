package br.com.foodtechproject.foodtech.controller;

import br.com.foodtechproject.foodtech.dto.ClienteDTO;
import br.com.foodtechproject.foodtech.dto.UsuarioDTO;
import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.exceptions.UsuarioExistenteException;
import br.com.foodtechproject.foodtech.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> pesquisarClientes(@RequestParam("page") int page, @RequestParam("size") int size){
        logger.info("GET -> /clientes");
        var clientes = this.clienteService.pesquisarClientes(page, size);
        return new ResponseEntity(clientes.getContent(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDTO> pesquisarCliente(@PathVariable Long id){
        logger.info("GET -> /clientes/"+id);
        Optional<Cliente> clienteOptional = this.clienteService.pesquisarClientePorId(id);

        if(clienteOptional.isPresent()){
            var cliente = clienteOptional.get();
            ClienteDTO clienteDTO = new ClienteDTO(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getEmail(),
                    cliente.getDataUltimaAtualizacao(),
                    cliente.getLogin(),
                    cliente.getEndereco()
            );
            return ResponseEntity.ok(clienteDTO);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Void> salvarCliente(@RequestBody UsuarioDTO usuarioDTO) {
        logger.info("POST -> /clientes");
        var clientes = usuarioDTO.mapearCliente();
        this.clienteService.salvarCliente(clientes);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        logger.info("DELETE -> /clientes/"+id);
        Optional<Cliente> clientes = this.clienteService.pesquisarClientePorId(id);

        if(clientes.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        this.clienteService.excluirCliente(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<String> handleUsuarioExistenteException(UsuarioExistenteException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
