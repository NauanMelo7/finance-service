package com.nm7.finance_service.controller;
import com.nm7.finance_service.domain.category.Category;
import com.nm7.finance_service.dto.categories.CategoriesCreateDTO;
import com.nm7.finance_service.dto.categories.CategoriesResponseDTO;
import com.nm7.finance_service.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoriesResponseDTO> createCategory(@Valid @RequestBody CategoriesCreateDTO body){
        CategoriesResponseDTO createCategory = categoryService.createCategory(body);

        return ResponseEntity.ok(createCategory);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoriesResponseDTO>> findAllCategories() {
        return ResponseEntity.ok(this.categoryService.findAllCategories());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoriesResponseDTO> findCategoryById(@PathVariable UUID id){
        return ResponseEntity.ok(this.categoryService.findCategoryById(id));
    }

    @PatchMapping("/categories/{id}/inactivate")
    public ResponseEntity<CategoriesResponseDTO> inactivateCategory(@PathVariable UUID id){
        return ResponseEntity.ok(this.categoryService.inactivateCategory(id));
    }
}
