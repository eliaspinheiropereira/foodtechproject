package br.com.foodtechproject.foodtech.repositories;

import br.com.foodtechproject.foodtech.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
