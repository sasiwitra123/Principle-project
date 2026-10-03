package com.example.costumerentalsystem.mapper;

import org.springframework.stereotype.Component;

import com.example.costumerentalsystem.domain.entity.Category;
import com.example.costumerentalsystem.dto.request.CategoryRequest;
import com.example.costumerentalsystem.dto.response.CategoryResponse;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }

    public void apply(Category category, CategoryRequest request) {
        category.setName(request.name().trim());
        category.setDescription(request.description());
    }
}
