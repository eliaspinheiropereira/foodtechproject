package br.com.foodtechproject.foodtech.repositories;

import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.entities.Endereco;
import br.com.foodtechproject.foodtech.entities.Login;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void salvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("elias pereira");
        cliente.setEmail("eliaspereira@gmail.com");

        Login login = new Login();
        login.setLogin("deckard");
        login.setSenha("12345");

        Endereco endereco = new Endereco();
        endereco.setLogradouro("rua nova");
        endereco.setBairro("pedreira");
        endereco.setCidade("belém");
        endereco.setEstado("PA");
        endereco.setCep("66083-442");
        endereco.setNumero("688");

        cliente.setLogin(login);
        cliente.setEndereco(endereco);

        var clienteSalvo = this.clienteRepository.save(cliente);
        System.out.println("Cliente salvo: "+clienteSalvo);
    }

    @Test
    public void atualizarCliente(){
        Long id = 2L;

        Optional<Cliente> cliente = this.clienteRepository.findById(id);

        if(cliente.isPresent()){
            Cliente clienteAtualizado = cliente.get();
            clienteAtualizado.setNome("Joyce Pereira");
            clienteAtualizado.setEmail("joycepereira@gmail.com");
            clienteAtualizado.setDataUltimaAtualizacao(LocalDateTime.now());

            Endereco endereco = new Endereco();
            endereco.setLogradouro("rua nova");
            endereco.setBairro("pedreira");
            endereco.setCidade("belém");
            endereco.setEstado("PA");
            endereco.setCep("66083-442");
            endereco.setNumero("688");

            Login login = new Login();
            login.setLogin("joyceRebelde");
            login.setSenha("12345");

            clienteAtualizado.setLogin(login);
            clienteAtualizado.setEndereco(endereco);
            this.clienteRepository.save(clienteAtualizado);
        }
    }

    @Test
    public void deletarCliente(){
        Long id = 5L;
        this.clienteRepository.deleteById(id);
    }

    @Test
    public void buscarCliente() {
        Pageable peageable = PageRequest.of(0, 10);
        Page<Cliente> clientes = this.clienteRepository.findAll(peageable);

        for(Cliente clienteEncontrado : clientes) {
            System.out.println(clienteEncontrado.getNome());
            System.out.println(clienteEncontrado.getEmail());
            System.out.println(clienteEncontrado.getDataUltimaAtualizacao());

            if(clienteEncontrado.getLogin() != null) {
                System.out.println(clienteEncontrado.getLogin().getLogin());
                System.out.println(clienteEncontrado.getLogin().getSenha());
            }

            if(clienteEncontrado.getEndereco() != null) {
                System.out.println(clienteEncontrado.getEndereco().getLogradouro());
                System.out.println(clienteEncontrado.getEndereco().getBairro());
                System.out.println(clienteEncontrado.getEndereco().getCidade());
                System.out.println(clienteEncontrado.getEndereco().getEstado());
                System.out.println(clienteEncontrado.getEndereco().getCep());
                System.out.println(clienteEncontrado.getEndereco().getNumero());
            }

            System.out.println();
            System.out.println("---");
        }
    }
}