package br.com.pedroalano.my_pocket.service;

import br.com.pedroalano.my_pocket.dto.TransactionRequest;
import br.com.pedroalano.my_pocket.dto.TransactionResponse;
import br.com.pedroalano.my_pocket.enumtype.TransactionStatus;
import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import br.com.pedroalano.my_pocket.model.Category;
import br.com.pedroalano.my_pocket.model.Transaction;
import br.com.pedroalano.my_pocket.model.User;
import br.com.pedroalano.my_pocket.repository.CategoryRepository;
import br.com.pedroalano.my_pocket.repository.TransactionRepository;
import br.com.pedroalano.my_pocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    public List<TransactionResponse> findByStatusAndTypeAndUserIdOrderByDateDesc(
        TransactionStatus status,
        TransactionType type,
        Long userId,
        Integer month,
        Integer year
    ){
       return transactionRepository.findByStatusAndTypeAndUserIdOrderByDateDesc(status,type,userId,month,year)
               .stream().map(TransactionResponse::from).toList();
    }

    public List<TransactionResponse> findByUserIdOrderByDateDesc(Long userId, Integer month, Integer year) {
        return transactionRepository.findByUseIdOrderByDateDesc(userId,month,year)
                .stream().map(TransactionResponse::from).toList();
    }

    public TransactionResponse get(Long id) {
        return transactionRepository.findById(id).map(TransactionResponse::from)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public TransactionResponse create(Long userId, TransactionRequest request) {
        User user = getUserId(userId);
        Category category = getCategoryId(request.category());

        var transaction = Transaction.builder()
                .date(request.date())
                .value(request.value())
                .description(request.description())
                .status(request.status())
                .type(request.type())
                .category(category)
                .user(user)
                .build();

        var saved = transactionRepository.save(transaction);

        return TransactionResponse.from(saved);
    }

    public TransactionResponse update(Long id, Long userId, TransactionRequest request) {
        var transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        User user = getUserId(userId);
        Category category = getCategoryId(request.category());

        transaction.setDate(request.date());
        transaction.setValue(request.value());
        transaction.setDescription(request.description());
        transaction.setStatus(request.status());
        transaction.setType(request.type());
        transaction.setCategory(category);
        transaction.setUser(user);

        var updated = transactionRepository.save(transaction);

        return TransactionResponse.from(updated);
    }

    public void delete(Long id) {
        var transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        transactionRepository.delete(transaction);
    }

    private User getUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Category getCategoryId(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
