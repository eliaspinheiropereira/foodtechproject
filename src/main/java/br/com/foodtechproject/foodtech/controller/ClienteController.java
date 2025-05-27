package br.com.foodtechproject.foodtech.controller;

import br.com.foodtechproject.foodtech.dto.UsuarioDTO;
import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(@RequestParam("page") int page, @RequestParam("size") int size){
        logger.info("GET -> /clientes");
        var clientes = this.clienteService.pesquisarClientes(page, size);
        return new ResponseEntity<>(clientes.getContent(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> salvarCliente(@RequestBody UsuarioDTO usuarioDTO) {
        logger.info("POST -> /clientes");
        this.clienteService.salvarCliente(usuarioDTO);
        return ResponseEntity.status(201).build();
    }
}
