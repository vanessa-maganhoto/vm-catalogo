package com.vanessamatos.vmcatalogo.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
@SQLDelete(sql = "UPDATE tb_category SET deleted=true, deleted_at=now() WHERE id=?")
@Where(clause = "deleted=false")
public class Category implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant deletedAt;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();
    public Category(){}

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    @PrePersist
    public void prePersist(){

        createdAt = Instant.now();
        deleted = false;
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = Instant.now();
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
