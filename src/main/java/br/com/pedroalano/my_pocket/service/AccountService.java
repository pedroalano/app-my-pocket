package br.com.pedroalano.my_pocket.service;

import br.com.pedroalano.my_pocket.dto.AccountRequest;
import br.com.pedroalano.my_pocket.dto.AccountResponse;
import br.com.pedroalano.my_pocket.model.Account;
import br.com.pedroalano.my_pocket.model.User;
import br.com.pedroalano.my_pocket.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<AccountResponse> findAllByUser(User user) {
        return accountRepository.findByUserIdOrderByOrder(user.getId()).stream()
                .map(AccountResponse::from).toList();
    }

    public AccountResponse get(Long id) {
        return accountRepository.findById(id).map(AccountResponse::from)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public AccountResponse create(User user, AccountRequest request) {
        var account = Account.builder()
                .name(request.name())
                .user(user)
                .build();

        var saved = accountRepository.save(account);

        return AccountResponse.from(saved);
    }

    public AccountResponse update(Long id, User user, AccountRequest request ) {
        var account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setName(request.name());
        account.setUser(user);

        var updated = accountRepository.save(account);

        return AccountResponse.from(updated);
    }

    public void delete(Long id) {
        var account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        accountRepository.delete(account);
    }
}
