package com.vanessamatos.vmcatalogo.services;

import com.vanessamatos.vmcatalogo.entities.Category;
import com.vanessamatos.vmcatalogo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}