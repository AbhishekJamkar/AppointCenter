package com.zosh.controller;

import com.zosh.modal.Category;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.UserDTO;
import com.zosh.service.CategoryService;
import com.zosh.service.clients.BusinessFeignClient;
import com.zosh.service.clients.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories/business-owner")
@RequiredArgsConstructor
public class BusinessCategoryController {

    private final CategoryService categoryService;
    private final BusinessFeignClient businessService;


    @PostMapping
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category,
            @RequestHeader("Authorization") String jwt) throws Exception {
        BusinessDTO business=businessService.getBusinessByOwner(jwt).getBody();

        Category savedCategory = categoryService.saveCategory(category, business);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
}
