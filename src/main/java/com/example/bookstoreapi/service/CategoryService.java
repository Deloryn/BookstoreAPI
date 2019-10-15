package com.example.bookstoreapi.service;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.entity.Category;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.repository.CategoryRepository;
import com.example.bookstoreapi.web.dto.Category.CategoryCreateOrUpdateDTO;
import com.example.bookstoreapi.web.dto.Category.CategoryInfoDTO;
import com.example.bookstoreapi.web.dto.book.BasicBookInfoDTO;
import com.example.bookstoreapi.web.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                           BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    public List<CategoryInfoDTO> getAllCategoriesInfos() {
        return categoryRepository
                .findAll()
                .stream()
                .map(CategoryInfoDTO::new)
                .collect(Collectors.toList());
    }

    public CategoryInfoDTO getCategoryInfoById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class, id));
        return new CategoryInfoDTO(category);
    }

    public List<BasicBookInfoDTO> getCategoryBooksInfo(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class, id))
                .getBooks()
                .stream()
                .map(BasicBookInfoDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class, id));
        categoryRepository.delete(category);
    }

    public Long createCategoryFrom(CategoryCreateOrUpdateDTO dto) {
        Category category = mapToCategory(dto);
        categoryRepository.save(category);
        return category.getId();
    }

    public void updateCategory(Long id, CategoryCreateOrUpdateDTO dto) {
        Category anotherCategory = mapToCategory(dto);
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class, id));
        existingCategory.updateData(anotherCategory);
        categoryRepository.save(existingCategory);
    }

    private Category mapToCategory(CategoryCreateOrUpdateDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());

        List<Book> books = getBooksByIds(dto.getBooksIds());
        category.setBooks(books);

        return category;
    }

    private List<Book> getBooksByIds(List<Long> booksIds) {
        return booksIds
                .stream()
                .map(id -> bookRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(Book.class, id)))
                .collect(Collectors.toList());
    }
}
