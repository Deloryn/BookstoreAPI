package com.example.bookstoreapi.web.controller;

import com.example.bookstoreapi.service.CategoryService;
import com.example.bookstoreapi.web.dto.Category.CategoryCreateOrUpdateDTO;
import com.example.bookstoreapi.web.dto.Category.CategoryInfoDTO;
import com.example.bookstoreapi.web.dto.book.BasicBookInfoDTO;
import com.example.bookstoreapi.web.error.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<List<CategoryInfoDTO>> getAllCategoriesInfos() {
        List<CategoryInfoDTO> dtos = categoryService.getAllCategoriesInfos();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<CategoryInfoDTO> getCategoryInfoById(@PathVariable Long id) {
        CategoryInfoDTO dto = categoryService.getCategoryInfoById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/categories/{id}/books")
    public ResponseEntity<List<BasicBookInfoDTO>> getCategoryBooksInfo(@PathVariable Long id) {
        List<BasicBookInfoDTO> dtos = categoryService.getCategoryBooksInfo(id);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<Long> createCategory(@Valid @RequestBody CategoryCreateOrUpdateDTO dto,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldErrors());
        }
        Long id = categoryService.createCategoryFrom(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping(value = "/categories/{id}")
    public ResponseEntity<Long> updateCategory(@PathVariable Long id,
                                             @Valid @RequestBody CategoryCreateOrUpdateDTO dto,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldErrors());
        }
        categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Long> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(id);
    }
}
