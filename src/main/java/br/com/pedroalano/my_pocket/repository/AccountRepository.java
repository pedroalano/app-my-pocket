package br.com.pedroalano.my_pocket.repository;

import br.com.pedroalano.my_pocket.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserIdOrderByOrder(Long userId);
}
