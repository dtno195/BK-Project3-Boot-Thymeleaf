package com.sd.entity;
import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "jhi_size")
    private Integer size;

    @Column(name = "prize")
    private Double prize;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "gender")
    private String gender;

    @Column(name = "status")
    private String status;

    @Column(name = "create_at")
    private ZonedDateTime createAt;

    @ManyToOne
    private Producer producer;

    @ManyToOne
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public Product size(Integer size) {
        this.size = size;
        return this;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getPrize() {
        return prize;
    }

    public Product prize(Double prize) {
        this.prize = prize;
        return this;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    public String getImage() {
        return image;
    }

    public Product image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public Product description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public Product createAt(ZonedDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public Producer getProducer() {
        return producer;
    }

    public Product producer(Producer producer) {
        this.producer = producer;
        return this;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Category getCategory() {
        return category;
    }

    public Product category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", size=" + getSize() +
                ", prize=" + getPrize() +
                ", image='" + getImage() + "'" +
                ", description='" + getDescription() + "'" +
                ", createAt='" + getCreateAt() + "'" +
                "}";
    }
}