package br.com.pedroalano.my_pocket.model;

import br.com.pedroalano.my_pocket.enumtype.TransactionStatus;
import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "transactions")
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private BigDecimal value;

    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionType  type;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @ManyToOne
    private Account account;
}
