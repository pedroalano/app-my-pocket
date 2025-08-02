package br.com.pedroalano.my_pocket.controller;

import br.com.pedroalano.my_pocket.dto.AccountRequest;
import br.com.pedroalano.my_pocket.dto.AccountResponse;
import br.com.pedroalano.my_pocket.model.User;
import br.com.pedroalano.my_pocket.repository.AccountRepository;
import br.com.pedroalano.my_pocket.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> list(@AuthenticationPrincipal User user) {
        var accounts = accountService.findAllByUser(user);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.get(id));
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@AuthenticationPrincipal User user, @Valid @RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.create(user,request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable Long id, @AuthenticationPrincipal User user, @Valid @RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.update(id,user,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
