package br.com.foodtechproject.foodtech.repositories;

import br.com.foodtechproject.foodtech.entities.Cliente;
import br.com.foodtechproject.foodtech.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByNomeAndEmail(String nome, String email);
}
