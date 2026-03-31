package com.nm7.finance_service.controller;

import com.nm7.finance_service.domain.account.Account;
import com.nm7.finance_service.domain.category.Category;
import com.nm7.finance_service.dto.categories.CategoriesCreateDTO;
import com.nm7.finance_service.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoriesCreateDTO body){
        Category createAccount = categoryService.createCategory(body);

        return ResponseEntity.ok(createAccount);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAllCategories() {
        return ResponseEntity.ok(this.categoryService.findAllCategories());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable UUID id){
        return ResponseEntity.ok(this.categoryService.findCategoryById(id));
    }
}
