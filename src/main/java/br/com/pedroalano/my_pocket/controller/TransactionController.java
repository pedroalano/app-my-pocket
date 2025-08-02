package br.com.pedroalano.my_pocket.controller;

import br.com.pedroalano.my_pocket.dto.TransactionRequest;
import br.com.pedroalano.my_pocket.dto.TransactionResponse;
import br.com.pedroalano.my_pocket.enumtype.TransactionStatus;
import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import br.com.pedroalano.my_pocket.model.User;
import br.com.pedroalano.my_pocket.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/expenses/planned")
    public ResponseEntity<List<TransactionResponse>> listTransactionExpensesAndPlanned(
            @AuthenticationPrincipal Object userPrincipal,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "account", required = false) Long account
    ) {
        var user = (User) userPrincipal;
        var transactions = transactionService.findByStatusAndTypeAndUserIdOrderByDateDesc(
                TransactionStatus.PLANNED,
                TransactionType.EXPENSE,
                user.getId(),
                month,
                year,
                account
        );

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/expenses/actual")
    public ResponseEntity<List<TransactionResponse>> listTransactionExpensesAndActual(
            @AuthenticationPrincipal Object userPrincipal,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "account", required = false) Long account

    ) {
        var user = (User) userPrincipal;
        var transactions = transactionService.findByStatusAndTypeAndUserIdOrderByDateDesc(
                TransactionStatus.ACTUAL,
                TransactionType.EXPENSE,
                user.getId(),
                month,
                year,
                account
        );

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/incomes/planned")
    public ResponseEntity<List<TransactionResponse>> listTransactionIncomesAndPlanned(
            @AuthenticationPrincipal Object userPrincipal,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "account", required = false) Long account
    ) {
        var user = (User) userPrincipal;
        var transactions = transactionService.findByStatusAndTypeAndUserIdOrderByDateDesc(
                TransactionStatus.PLANNED,
                TransactionType.INCOME,
                user.getId(),
                month,
                year,
                account
        );

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/incomes/actual")
    public ResponseEntity<List<TransactionResponse>> listTransactionIncomesAndActual(
            @AuthenticationPrincipal Object userPrincipal,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "account", required = false) Long account
    ) {
        var user = (User) userPrincipal;
        var transactions = transactionService.findByStatusAndTypeAndUserIdOrderByDateDesc(
                TransactionStatus.ACTUAL,
                TransactionType.INCOME,
                user.getId(),
                month,
                year,
                account
        );

        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> listTransaction(
            @AuthenticationPrincipal Object userPrincipal,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "account", required = false) Long account
    ) {
       var user = (User) userPrincipal;
       var transactions = transactionService.findByUserIdOrderByDateDesc(user.getId(),month,year,account);

       return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.get(id));
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody TransactionRequest request
            ) {
       return ResponseEntity.ok(transactionService.create(user, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> update(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @Valid @RequestBody TransactionRequest request
    ) {
        return ResponseEntity.ok(transactionService.update(id, user, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
