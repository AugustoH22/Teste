package com.adesp.festival.artist.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity(name = "artisti")
@EntityListeners(AuditingEntityListener.class)
public class Artist {

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
            name = "cpf",
            length = 64,
            nullable = false
    )
    private String cpf;

    @Column(
            name = "rg",
            length = 64,
            nullable = false
    )
    private String rg;

    @Column(
            name = "city",
            length = 16,
            nullable = false
    )
    private String city;

    @Column(
            name = "uf",
            length = 16,
            nullable = false
    )
    private String uf;

    @Column(
            name = "email",
            length = 15,
            nullable = false
    )
    private String email;

    @Column(
            name = "telephone",
            length = 15,
            nullable = false
    )
    private String telephone;

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


    public Artist() {
    }

    public Artist(Long id, String name, String cpf, String rg, String city, String uf, String email, String telephone, Long createdBy, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.city = city;
        this.uf = uf;
        this.email = email;
        this.telephone = telephone;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Artist(String name, String cpf, String rg, String city, String uf, String email, String telephone, Long createdBy, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.city = city;
        this.uf = uf;
        this.email = email;
        this.telephone = telephone;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Artist(Long id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
}
