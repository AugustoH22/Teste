package com.adesp.festival.restaurant.domain.entities;

import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.restaurant.domain.enums.Category;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity(
        name = "restaurants"
)
@EntityListeners(AuditingEntityListener.class)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            length = 64,
            nullable = false
    )
    private String name;

    @Column(
            name = "responsible_name",
            length = 64,
            nullable = false
    )
    private String responsibleName;

    @Column(
            name = "cep",
            length = 8,
            nullable = false
    )
    private String cep;

    @Column(
            name = "address",
            length = 64,
            nullable = false
    )
    private String address;

    @Column(
            name = "neighborhood",
            length = 64,
            nullable = false
    )
    private String neighborhood;

    @Column(
            name = "number",
            length = 6,
            nullable = false
    )
    private String number;

    @Column(
            name = "created_by"
    )
    @CreatedBy
    private Long createdBy;

    @Column(
            name = "is_active",
            nullable = false
    )
    private Boolean isActive;

    @Column(
            name = "is_deleted",
            nullable = false
    )
    private Boolean isDeleted;

    @CreationTimestamp
    @Column(
            name = "created_at"
    )
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(
            name = "updated_at"
    )
    private Timestamp updatedAt;

    @Column(
            name = "category",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Category category;


    public Restaurant(Long id, String name, String responsibleName, String cep, String address, String neighborhood, String number, Long createdBy, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt, Category category) {
        this.id = id;
        this.name = name;
        this.responsibleName = responsibleName;
        this.cep = cep;
        this.address = address;
        this.neighborhood = neighborhood;
        this.number = number;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }

    public Restaurant(String name, String responsibleName, String cep, String address, String neighborhood, String number, Long createdBy, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt, Category category) {
        this.name = name;
        this.responsibleName = responsibleName;
        this.cep = cep;
        this.address = address;
        this.neighborhood = neighborhood;
        this.number = number;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }

    public Restaurant(Long id) {
        this.id = id;
    }

    public Restaurant() {
    }

    @PrePersist
    public void prePersistRestaurant(){
        this.isDeleted = false;
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

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Category getCategory() {return category; }

    public void setCategory(Category category) { this.category = category; }
}
