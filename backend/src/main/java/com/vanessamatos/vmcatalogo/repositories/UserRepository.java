package com.vanessamatos.vmcatalogo.repositories;

import com.vanessamatos.vmcatalogo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
