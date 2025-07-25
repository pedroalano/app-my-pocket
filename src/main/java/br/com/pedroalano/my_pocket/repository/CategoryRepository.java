package br.com.pedroalano.my_pocket.repository;

import br.com.pedroalano.my_pocket.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.user.id = :userId ORDER BY c.order")
    List<Category> findByUser(@Param("userId") Long userId);
}
