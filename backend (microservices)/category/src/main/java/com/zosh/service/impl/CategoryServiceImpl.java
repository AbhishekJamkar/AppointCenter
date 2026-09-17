package com.zosh.service.impl;

import com.zosh.modal.Category;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.repository.CategoryRepository;
import com.zosh.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, BusinessDTO business) {

        Category newCategory=new Category();
        newCategory.setName(category.getName());
        newCategory.setImage(category.getImage());
        newCategory.setBusinessId(business.getId());


        return categoryRepository.save(newCategory);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Set<Category> getAllCategoriesByBusiness(Long businessId) {
        return categoryRepository.findByBusinessId(businessId);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        return categoryRepository.findById(id)
                .orElseThrow(()->new Exception("category not found"));
    }

    @Override
    public Category updateCategory(Long id,Category category) throws Exception {
        Category existingCategory=getCategoryById(id);

        if(category.getName()!=null){
            existingCategory.setName(category.getName());
        }
        if(category.getImage()!=null){
            existingCategory.setImage(category.getImage());
        }

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        getCategoryById(id);
        categoryRepository.deleteById(id);


    }
}
