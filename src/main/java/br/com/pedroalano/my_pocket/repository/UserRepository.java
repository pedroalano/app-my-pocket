package br.com.pedroalano.my_pocket.repository;

import br.com.pedroalano.my_pocket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
