package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.CategoryDTO;
import com.alkemy.somosmas.dtos.ListaCategoryDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;
import com.alkemy.somosmas.exceptions.PageEmptyException;
import com.alkemy.somosmas.models.Category;
import com.alkemy.somosmas.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping()
    public ResponseEntity<Map<String, Object>>getAll(@RequestParam int page){
        Map<String, Object> response = null;
        try {
            response = this.categoryService.getAllCategoriesByPage(page);
        } catch (NotAcceptableArgumentException e) {
            response= new HashMap<>();
            response.put("Error",e.getMessage());
            return ResponseEntity.ok().body(response);
        } catch (PageEmptyException e) {
            response= new HashMap<>();
            response.put("Error",e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object>getDetalle(@PathVariable Long id) {
        CategoryDTO categoryDTO = null;
        try {
            categoryDTO = this.categoryService.getCategoryById(id);
        } catch (ModelNotFoundException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@RequestBody CategoryDTO newCategoryDTO, @PathVariable Long id) {
        CategoryDTO categoryDTO = null;
        try{
            this.categoryService.updateCategory(newCategoryDTO,id);
        }catch (ModelNotFoundException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        CategoryDTO categoryDTO = null;
        try{
            this.categoryService.delete(id);
        }catch (ModelNotFoundException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return new ResponseEntity<String>("La Categoria de id n°: "+id+" ha sido eliminada.",HttpStatus.OK);
    }
}
