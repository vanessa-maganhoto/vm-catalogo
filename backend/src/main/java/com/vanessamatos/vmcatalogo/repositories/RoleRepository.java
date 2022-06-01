package com.vanessamatos.vmcatalogo.repositories;

import com.vanessamatos.vmcatalogo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
