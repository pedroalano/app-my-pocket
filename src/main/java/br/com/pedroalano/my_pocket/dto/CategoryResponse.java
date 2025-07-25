package br.com.pedroalano.my_pocket.dto;

import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import br.com.pedroalano.my_pocket.model.Category;

public record CategoryResponse(
        Long categoryId,
        String name,
        TransactionType categoryType,
        Long userId,
        Long order
) {
    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getCategoryType(),
                category.getUser().getId(),
                category.getOrder()
        );
    }
}
