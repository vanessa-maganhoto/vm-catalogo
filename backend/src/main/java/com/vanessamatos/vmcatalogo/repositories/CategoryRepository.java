package com.vanessamatos.vmcatalogo.repositories;

import com.vanessamatos.vmcatalogo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
