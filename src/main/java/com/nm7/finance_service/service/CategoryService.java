package com.nm7.finance_service.service;

import com.nm7.finance_service.domain.category.Category;
import com.nm7.finance_service.dto.categories.CategoriesCreateDTO;
import com.nm7.finance_service.exception.BusinessException;
import com.nm7.finance_service.repository.account.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoriesCreateDTO body){

        if(categoryRepository.existsByName(body.name())) {
            throw new BusinessException("The category already has a name", HttpStatus.NOT_FOUND);
        }

        Category createCategory = new Category(
                body.name(),
                body.type()
        );

        this.categoryRepository.save(createCategory);

        return createCategory;

    }
}
