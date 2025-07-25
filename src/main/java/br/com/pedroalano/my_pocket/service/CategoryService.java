package br.com.pedroalano.my_pocket.service;

import br.com.pedroalano.my_pocket.dto.CategoryRequest;
import br.com.pedroalano.my_pocket.dto.CategoryResponse;
import br.com.pedroalano.my_pocket.model.Category;
import br.com.pedroalano.my_pocket.model.User;
import br.com.pedroalano.my_pocket.repository.CategoryRepository;
import br.com.pedroalano.my_pocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    public List<CategoryResponse> findAllByUser(Long userId) {
        return categoryRepository.findByUser(userId).stream()
                .map(CategoryResponse::from).toList();
    }

    public CategoryResponse get(Long id) {
        return categoryRepository.findById(id).map(CategoryResponse::from)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public CategoryResponse create(Long userId,CategoryRequest request) {
        User user = getUserId(userId);
        var category = Category.builder()
                .name(request.name())
                .categoryType(request.categoryType())
                .user(user)
                .build();

        var saved = categoryRepository.save(category);

        return CategoryResponse.from(saved);
    }

    public CategoryResponse update(Long id, Long userId, CategoryRequest request) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        User user = getUserId(userId);

        category.setName(request.name());
        category.setCategoryType(request.categoryType());
        category.setUser(user);

        var updated = categoryRepository.save(category);

        return CategoryResponse.from(updated);
    }

    public void delete(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }

    private User getUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
