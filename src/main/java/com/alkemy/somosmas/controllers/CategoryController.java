package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.CategoryDTO;
import com.alkemy.somosmas.dtos.ListaCategoryDTO;
import com.alkemy.somosmas.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO category){
        CategoryDTO categoryGuardado = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryGuardado);
    }
}
