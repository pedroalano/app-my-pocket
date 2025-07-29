package br.com.pedroalano.my_pocket.dto;

import br.com.pedroalano.my_pocket.model.Account;

public record AccountResponse(
        Long accountId,
        String name,
        Long userId
) {

    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getName(),
                account.getUser().getId()
        );
    }
}
