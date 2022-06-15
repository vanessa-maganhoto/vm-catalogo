package com.vanessamatos.vmcatalogo.resources;

import com.vanessamatos.vmcatalogo.dto.CategoryDTO;
import com.vanessamatos.vmcatalogo.entities.Category;
import com.vanessamatos.vmcatalogo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable){

        Page<CategoryDTO> list = categoryService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        CategoryDTO categoryDTO = categoryService.findById(id);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO categoryDTO){
        categoryDTO = categoryService.insert(categoryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        categoryDTO = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
