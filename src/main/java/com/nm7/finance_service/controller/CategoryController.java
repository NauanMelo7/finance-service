package com.nm7.finance_service.controller;

import com.nm7.finance_service.domain.account.Account;
import com.nm7.finance_service.domain.category.Category;
import com.nm7.finance_service.dto.categories.CategoriesCreateDTO;
import com.nm7.finance_service.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoriesCreateDTO body){
        Category createAccount = categoryService.createCategory(body);

        return ResponseEntity.ok(createAccount);
    }
}
