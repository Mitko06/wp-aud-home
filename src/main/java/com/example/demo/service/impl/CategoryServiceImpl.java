package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.repository.InMemoryCategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    public final InMemoryCategoryRepository categoryRepository;

    public CategoryServiceImpl(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String name, String description) {
        if (name==null || name.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        Category category = new Category(name,description);
        categoryRepository.saveCategory(category);
        return category;
    }

    @Override
    public Category update(String name, String description) {
        if (name==null || name.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        Category category = new Category(name,description);
        categoryRepository.saveCategory(category);
        return category;
    }

    @Override
    public void delete(String name) {
        if (name==null || name.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        categoryRepository.delete(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        if (searchText==null || searchText.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        return categoryRepository.search(searchText);
    }
}
