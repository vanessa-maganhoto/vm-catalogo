package com.vanessamatos.vmcatalogo.services;

import com.vanessamatos.vmcatalogo.dto.CategoryDTO;
import com.vanessamatos.vmcatalogo.dto.ProductDTO;
import com.vanessamatos.vmcatalogo.entities.Category;
import com.vanessamatos.vmcatalogo.entities.Product;
import com.vanessamatos.vmcatalogo.repositories.CategoryRepository;
import com.vanessamatos.vmcatalogo.repositories.ProductRepository;
import com.vanessamatos.vmcatalogo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.Filter;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<Product> list = productRepository.findAll(pageRequest);
        return list.map(x -> new ProductDTO(x));
    }


//    public List<ProductDTO> findAll(){
//        List<Product> list = productRepository.findAll();
//        return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
//    }
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> productObj = productRepository.findById(id);
        Product product = productObj.orElseThrow(()-> new ResourceNotFoundException("Entidade não encontrada"));
        return new ProductDTO(product, product.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO){
        Product newProduct = new Product();
        copyDtoToNewProduct(productDTO, newProduct);

        newProduct = productRepository.save(newProduct);
        return new ProductDTO(newProduct);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO){
        try{
            Product newProduct = productRepository.getById(id);
            copyDtoToNewProduct(productDTO, newProduct);
            newProduct = productRepository.save(newProduct);
            return new ProductDTO(newProduct);
        } catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("Id não existe " + id);
        }
    }

    public void delete(Long id){
        try{
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw  new ResourceNotFoundException("Id não encontrado " + id);
        }
        catch (DataIntegrityViolationException e){
            throw  new DataIntegrityViolationException("Violação de integridade");
        }
    }

    private void copyDtoToNewProduct(ProductDTO productDTO, Product newProduct){
        newProduct.setName(productDTO.getName());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setDate(productDTO.getDate());
        newProduct.setImgUrl(productDTO.getImgUrl());
        newProduct.setPrice(productDTO.getPrice());

        newProduct.getCategories().clear();
        for(CategoryDTO catDTO : productDTO.getCategories()){
            Category category = categoryRepository.getById(catDTO.getId());
            newProduct.getCategories().add(category);
        }
    }
}
