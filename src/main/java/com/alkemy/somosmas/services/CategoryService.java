package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.CategoryDTO;
import com.alkemy.somosmas.dtos.ListaCategoryDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;
import com.alkemy.somosmas.exceptions.PageEmptyException;
import com.alkemy.somosmas.models.Category;
import com.alkemy.somosmas.repositories.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<ListaCategoryDTO> getAllCategories() {
        List<Category> CategoryEntities = categoryRepository.findAll();
        List<ListaCategoryDTO> listaDTO = new ArrayList<>();
        for (Category category: CategoryEntities){
            listaDTO.add(mapper.convertValue(category,ListaCategoryDTO.class));
        }
        System.out.println("categorías buscadas");
        return listaDTO;
    }
    
    public CategoryDTO getCategoryById(Long id) throws ModelNotFoundException {
        Category model = this.categoryRepository.findById(id).orElse(null);
        if(model==null){
            //Excepcion de tipo check heredar de la clase exception
            throw new ModelNotFoundException(id,"Category");
        }

        CategoryDTO categoryDTO = mapper.convertValue(model,CategoryDTO.class);
        return categoryDTO;
    }

    public CategoryDTO updateCategory(CategoryDTO newCategoryDTO,Long id) throws ModelNotFoundException {
        Category model = this.categoryRepository.findById(id).orElse(null);
        if(model==null){
            //Excepcion de tipo check heredar de la clase exception
            throw new ModelNotFoundException(id,"Category");
        }
        CategoryDTO categoryDTO = mapper.convertValue(model,CategoryDTO.class);

        categoryDTO.setName(newCategoryDTO.getName());
        categoryDTO.setDescription(newCategoryDTO.getDescription());
        categoryDTO.setImage(newCategoryDTO.getImage());
        this.save(categoryDTO);
        System.out.println("categoría actualizada");

        return categoryDTO;
    }

    public void delete(Long id) throws ModelNotFoundException {

        Category model = this.categoryRepository.findById(id).orElse(null);
        if(model==null){
            //Excepcion de tipo check heredar de la clase exception
            throw new ModelNotFoundException(id,"Category");
        }
        this.categoryRepository.deleteById(id);
    }


    public Map<String, Object> getAllCategoriesByPage(int pageNo ) throws NotAcceptableArgumentException, PageEmptyException {

        if(pageNo<0){
            throw new NotAcceptableArgumentException("The pageNo must be positive");
        }


        Pageable pageable = PageRequest.of(pageNo,10);

        Page<Category> allCategoriesPage= categoryRepository.findAll(pageable);

        if(allCategoriesPage.isEmpty()){
            throw new PageEmptyException(pageNo, "categories");
        }


        List<Category> categoriesModel = allCategoriesPage.getContent();
        List<CategoryDTO> categoriesDtoReturned = categoriesModel
                            .stream()
                            .map(i->mapper.convertValue(i,CategoryDTO.class))
                            .collect(Collectors.toList());

        Map<String, Object> returnedMap = new HashMap<>();
        returnedMap.put("Categories", categoriesDtoReturned);
        returnedMap.put("currentPage",allCategoriesPage.getNumber());
        returnedMap.put("totalItems",allCategoriesPage.getTotalElements());
        returnedMap.put("totalPages",allCategoriesPage.getTotalPages());



        if (allCategoriesPage.hasNext()){
            returnedMap.put("nextPage","localhost:8080/categories?page="+(pageNo+1));
        }
        if (pageNo!=0){
            returnedMap.put("previousPage","localhost:8080/categories?page="+(pageNo-1));
        }


        return returnedMap;
    }
}
