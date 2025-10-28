package br.com.pedroalano.my_pocket.repository;

import br.com.pedroalano.my_pocket.dto.DashboardAggregationDTO;
import br.com.pedroalano.my_pocket.dto.DashboardResponse;
import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import br.com.pedroalano.my_pocket.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DashboardRepository  extends JpaRepository<Transaction,Long> {

    @Query(value = """
            SELECT t.type as type, EXTRACT(MONTH FROM t.date) as month, c.id as categoryId, c.name as categoryName, t.status as status, SUM(t.value) as total 
            FROM transactions t 
            JOIN categories c ON t.category_id = c.id 
            WHERE 
                EXTRACT(MONTH FROM t.date) = :month
                AND EXTRACT(YEAR FROM t.date) = :year 
                AND t.account_id = :account
            GROUP BY t.type, c.id, t.status, EXTRACT(MONTH FROM t.date) 
            ORDER BY month ASC, c.sort_order ASC
            """, nativeQuery = true)
    List<DashboardAggregationDTO> aggregateByTypeAndMonthFilterByMonthAndYearAndAccount(@Param("month") int month,@Param("year") int year, @Param("account") Long accountId);

    @Query(value = """
            SELECT
              EXTRACT(MONTH FROM t.date) AS month,
              c.id AS categoryId,
              c.name AS categoryName,
              t.type,
              COALESCE(SUM(t.value) FILTER (WHERE t.status = 'PLANNED'), 0) AS totalPlanned,
              COALESCE(SUM(t.value) FILTER (WHERE t.status = 'ACTUAL'), 0) AS totalActual,
              COALESCE(SUM(t.value) FILTER (WHERE t.status = 'PLANNED'), 0)
                  - COALESCE(SUM(t.value) FILTER (WHERE t.status = 'ACTUAL'), 0) AS difference
            FROM transactions t
            JOIN categories c ON t.category_id = c.id
            WHERE
              EXTRACT(MONTH FROM t.date) = :month
              AND EXTRACT(YEAR FROM t.date) = :year
              AND t.account_id = :account
              AND t.type = :type
            GROUP BY EXTRACT(MONTH FROM t.date), c.id, c.name, t.type
            ORDER BY month ASC, c.sort_order ASC
            """, nativeQuery = true)
    List<DashboardAggregationDTO> formatedByTypeAndMonthFilterByMonthAndYearAndAccount(@Param("month") int month, @Param("year") int year, @Param("account") Long accountId, @Param("type") String type);
}
