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
    public ResponseEntity<Page<Cliente>> buscarClientes(@RequestParam("page") int page, @RequestParam("size") int size){
        logger.info("GET -> /clientes");
        var clientes = this.clienteService.buscarClientes(page, size);
        return new ResponseEntity(clientes.getContent(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id){
        logger.info("GET -> /clientes/"+id);
        var cliente = this.clienteService.buscarClientePorId(id);;

        if(cliente == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Void> salvarCliente(@RequestBody UsuarioDTO usuarioDTO) {
        logger.info("POST -> /clientes");
        this.clienteService.salvarCliente(usuarioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        logger.info("DELETE -> /clientes/"+id);
        var cliente = this.clienteService.buscarClientePorId(id);

        if(cliente == null) {
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
