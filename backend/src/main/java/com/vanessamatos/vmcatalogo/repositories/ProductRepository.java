package com.vanessamatos.vmcatalogo.repositories;

import com.vanessamatos.vmcatalogo.entities.Category;
import com.vanessamatos.vmcatalogo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p " +
            "INNER JOIN p.categories cats " +
            "WHERE (COALESCE(:categories) IS NULL OR cats IN :categories) AND " +
            "(LOWER(p.name) LIKE LOWER (CONCAT('%',:name,'%')) )")
    Page<Product> find(List<Category> categories, String name, Pageable pageable);
}
