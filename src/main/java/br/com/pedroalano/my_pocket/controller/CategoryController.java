package br.com.pedroalano.my_pocket.controller;

import br.com.pedroalano.my_pocket.dto.CategoryRequest;
import br.com.pedroalano.my_pocket.dto.CategoryResponse;
import br.com.pedroalano.my_pocket.model.Category;
import br.com.pedroalano.my_pocket.model.User;
import br.com.pedroalano.my_pocket.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> list(@AuthenticationPrincipal User user) {
        var categories = categoryService.findAllByUser(user.getId());
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@AuthenticationPrincipal User user, @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryService.create(user,request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@AuthenticationPrincipal User user, @PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryService.update(id,user,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
