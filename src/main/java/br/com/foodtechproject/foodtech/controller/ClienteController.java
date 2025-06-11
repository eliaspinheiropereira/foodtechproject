package br.com.foodtechproject.foodtech.controller;

import br.com.foodtechproject.foodtech.dto.ClienteDTO;
import br.com.foodtechproject.foodtech.dto.ErroRespostaDTO;
import br.com.foodtechproject.foodtech.dto.UsuarioDTO;
import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.exceptions.RegistroDuplicadoException;
import br.com.foodtechproject.foodtech.exceptions.UsuarioNaoExistenteException;
import br.com.foodtechproject.foodtech.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> salvarCliente(
            @RequestBody UsuarioDTO usuarioDTO
    ) {
        logger.info("POST -> /clientes");
        try{
            this.clienteService.salvarCliente(usuarioDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RegistroDuplicadoException e){
            var erroDTO = ErroRespostaDTO.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizarCliente(
            @PathVariable(value = "id") Long id,
            @RequestBody UsuarioDTO usuarioDTO
    ){
        logger.info("PUT -> /clientes/"+id);
        try{
            this.clienteService.atualizarCliente(id, usuarioDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UsuarioNaoExistenteException e){
            var erroDTO = ErroRespostaDTO.naoEncontrado(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> excluirCliente(@PathVariable Long id) {
        logger.info("DELETE -> /clientes/"+id);
        try{
            this.clienteService.excluirCliente(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (UsuarioNaoExistenteException e){
            var erroDTO = ErroRespostaDTO.naoEncontrado(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
