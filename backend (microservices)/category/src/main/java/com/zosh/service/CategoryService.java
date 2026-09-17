package com.zosh.service;

import com.zosh.modal.Category;
import com.zosh.payload.dto.BusinessDTO;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    // Create or Update a Category
    Category saveCategory(Category category, BusinessDTO business);

    // Get all Categories
    List<Category> getAllCategories();

    Set<Category> getAllCategoriesByBusiness(Long id);

    // Get Category by ID
    Category getCategoryById(Long id) throws Exception;

    Category updateCategory(Long id,Category category) throws Exception;

    // Delete Category by ID
    void deleteCategory(Long id) throws Exception;
}
