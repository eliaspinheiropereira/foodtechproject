package br.com.foodtechproject.foodtech.repositories;

import br.com.foodtechproject.foodtech.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
