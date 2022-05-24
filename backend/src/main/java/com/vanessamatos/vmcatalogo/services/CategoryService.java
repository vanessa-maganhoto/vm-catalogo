package com.vanessamatos.vmcatalogo.services;

import com.vanessamatos.vmcatalogo.dto.CategoryDTO;
import com.vanessamatos.vmcatalogo.entities.Category;
import com.vanessamatos.vmcatalogo.repositories.CategoryRepository;
import com.vanessamatos.vmcatalogo.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = categoryRepository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> categoryObj = categoryRepository.findById(id);
        Category category = categoryObj.orElseThrow(()-> new EntityNotFoundException("Entidade não encontrada"));
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO categoryDTO){
        Category newCategory = new Category();
        newCategory.setName(categoryDTO.getName());
        newCategory = categoryRepository.save(newCategory);
        return new CategoryDTO(newCategory);
    }

}
