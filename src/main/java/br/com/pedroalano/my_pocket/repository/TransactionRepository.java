package br.com.pedroalano.my_pocket.repository;

import br.com.pedroalano.my_pocket.enumtype.TransactionStatus;
import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import br.com.pedroalano.my_pocket.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("""
            SELECT t FROM Transaction t
            WHERE t.user.id = :userId
             AND (:type IS NULL OR t.type = :type)
             AND (:status IS NULL OR t.status = :status)
             AND (:month IS NULL OR MONTH(t.date) = :month)
             AND (:year IS NULL OR YEAR(t.date) = :year)
            ORDER BY t.date DESC
            """)
    List<Transaction> findByStatusAndTypeAndUserIdOrderByDateDesc(
            @Param("status") TransactionStatus status,
            @Param("type") TransactionType type,
            @Param("userId") Long userId,
            @Param("month") Integer month,
            @Param("year") Integer year
    );

    @Query("""
            SELECT t FROM Transaction t
            WHERE t.user.id = :userId
             AND (:month IS NULL OR MONTH(t.date) = :month)
             AND (:year IS NULL OR YEAR(t.date) = :year)
            ORDER BY t.date DESC
           """)
    List<Transaction> findByUseIdOrderByDateDesc(
            @Param("userId") Long userId,
            @Param("month") Integer month,
            @Param("year") Integer year
    );

}
