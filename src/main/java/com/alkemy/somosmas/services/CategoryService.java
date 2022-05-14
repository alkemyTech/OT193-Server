package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.CategoryDTO;
import com.alkemy.somosmas.dtos.ListaCategoryDTO;
import com.alkemy.somosmas.models.Category;
import com.alkemy.somosmas.repositories.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ObjectMapper mapper;

    public CategoryDTO save(CategoryDTO dto){
        Category category = mapper.convertValue(dto,Category.class);
        categoryRepository.save(category);
        CategoryDTO categoryDTO = mapper.convertValue(category,CategoryDTO.class);
        System.out.println("categoría guardada");

        return categoryDTO;
    }
}
