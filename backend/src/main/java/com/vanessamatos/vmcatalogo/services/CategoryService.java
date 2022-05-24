package com.vanessamatos.vmcatalogo.services;

import com.vanessamatos.vmcatalogo.dto.CategoryDTO;
import com.vanessamatos.vmcatalogo.entities.Category;
import com.vanessamatos.vmcatalogo.repositories.CategoryRepository;
import com.vanessamatos.vmcatalogo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
        Page<Category> list = categoryRepository.findAll(pageRequest);
        return list.map(x -> new CategoryDTO(x));
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> categoryObj = categoryRepository.findById(id);
        Category category = categoryObj.orElseThrow(()-> new ResourceNotFoundException("Entidade não encontrada"));
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO categoryDTO){
        Category newCategory = new Category();
        newCategory.setName(categoryDTO.getName());
        newCategory = categoryRepository.save(newCategory);
        return new CategoryDTO(newCategory);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO categoryDTO){
        try{
            Category category = categoryRepository.getById(id);
            category.setName(categoryDTO.getName());
            category = categoryRepository.save(category);
            return new CategoryDTO(category);
        } catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("Id não existe " + id);
        }
    }

    public void delete(Long id){
        try{
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw  new ResourceNotFoundException("Id não encontrado " + id);
        }
        catch (DataIntegrityViolationException e){
            throw  new DataIntegrityViolationException("Violação de integridade");
        }
    }
}
