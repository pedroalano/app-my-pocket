package br.com.pedroalano.my_pocket.model;

import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "categories")
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TransactionType categoryType;

    @ManyToOne
    private User user;

    @Column(name = "sort_order")
    private Long order;
}
