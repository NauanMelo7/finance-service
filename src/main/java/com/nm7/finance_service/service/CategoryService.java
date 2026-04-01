package com.nm7.finance_service.service;

import com.nm7.finance_service.domain.category.Category;
import com.nm7.finance_service.domain.category.StatusCategory;
import com.nm7.finance_service.dto.categories.CategoriesCreateDTO;
import com.nm7.finance_service.dto.categories.CategoriesResponseDTO;
import com.nm7.finance_service.exception.BusinessException;
import com.nm7.finance_service.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public CategoriesResponseDTO createCategory(CategoriesCreateDTO body){

        if(categoryRepository.existsByName(body.name())) {
            throw new BusinessException("The category already has a name", HttpStatus.CONFLICT);
        }

        Category createCategory = this.categoryRepository.save(new Category(
                body.name(),
                body.type()
        ));

        return new CategoriesResponseDTO(
                createCategory.getId(),
                createCategory.getName(),
                createCategory.getType(),
                createCategory.getStatus(),
                createCategory.getCreatedAt(),
                createCategory.getUpdatedAt());

    }

    public List<CategoriesResponseDTO> findAllCategories(){

       List<Category> findAllCategory = this.categoryRepository.findAll();
       List<CategoriesResponseDTO> responseCategory = new ArrayList<>();

       for(Category parseCategory : findAllCategory) {

           CategoriesResponseDTO addCategory = new CategoriesResponseDTO(
                   parseCategory.getId(),
                   parseCategory.getName(),
                   parseCategory.getType(),
                   parseCategory.getStatus(),
                   parseCategory.getCreatedAt(),
                   parseCategory.getUpdatedAt()
           );

           responseCategory.add(addCategory);
       }


       return responseCategory;

    }

    public CategoriesResponseDTO findCategoryById(UUID id) {

        Category findCategory = this.categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Category is not found", HttpStatus.NOT_FOUND));
        return new CategoriesResponseDTO(
                findCategory.getId(),
                findCategory.getName(),
                findCategory.getType(),
                findCategory.getStatus(),
                findCategory.getCreatedAt(),
                findCategory.getUpdatedAt()
        );
    }

    public CategoriesResponseDTO inactivateCategory(UUID id) {

        Category findCategory = this.categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Category is not found", HttpStatus.NOT_FOUND));

        if(findCategory.getStatus().equals(StatusCategory.INACTIVE)) {
            throw new BusinessException("The Category is already inactive", HttpStatus.CONFLICT);
        }

        findCategory.setStatus(StatusCategory.INACTIVE);

        this.categoryRepository.save(findCategory);

        return new CategoriesResponseDTO(
                findCategory.getId(),
                findCategory.getName(),
                findCategory.getType(),
                findCategory.getStatus(),
                findCategory.getCreatedAt(),
                findCategory.getUpdatedAt()
        ) ;
    }
}
