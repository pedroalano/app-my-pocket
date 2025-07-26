package br.com.pedroalano.my_pocket.dto;

import br.com.pedroalano.my_pocket.enumtype.TransactionStatus;
import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import br.com.pedroalano.my_pocket.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
        Long transactionId,
        LocalDate date,
        BigDecimal value,
        String description,
        TransactionType type,
        TransactionStatus status,
        Long categoryId,
        Long userId
) {
    public static TransactionResponse from(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getDate(),
                transaction.getValue(),
                transaction.getDescription(),
                transaction.getType(),
                transaction.getStatus(),
                transaction.getCategory().getId(),
                transaction.getUser().getId()
        );
    }
}
