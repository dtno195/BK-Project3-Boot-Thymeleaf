package com.sd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Producer.
 */
@Entity
@Table(name = "producer")
public class Producer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "producer")
    private Set<Product> producerProducts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Producer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Producer description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProducerProducts() {
        return producerProducts;
    }

    public Producer producerProducts(Set<Product> products) {
        this.producerProducts = products;
        return this;
    }

    public Producer addProducerProduct(Product product) {
        this.producerProducts.add(product);
        product.setProducer(this);
        return this;
    }

    public Producer removeProducerProduct(Product product) {
        this.producerProducts.remove(product);
        product.setProducer(null);
        return this;
    }

    public void setProducerProducts(Set<Product> products) {
        this.producerProducts = products;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Producer producer = (Producer) o;
        if (producer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), producer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                "}";
    }
}
