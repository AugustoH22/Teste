package com.adesp.festival.tokens.domain.entities;

import com.adesp.festival.dishes.domain.entities.Dish;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity(
        name = "voting_token_batches"
)
@EntityListeners(AuditingEntityListener.class)
public class VotingTokenBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "quantity",
            nullable = false
    )
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private Dish dish;

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

    public VotingTokenBatch(Long id, Integer quantity, Dish dish, Long createdBy, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.quantity = quantity;
        this.dish = dish;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public VotingTokenBatch(Integer quantity, Dish dish, Long createdBy, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.quantity = quantity;
        this.dish = dish;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersistVotingTokenBatch(){
        this.isActive = true;
        this.isDeleted = false;
    }

    public VotingTokenBatch(Long id) {
        this.id = id;
    }

    public VotingTokenBatch() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
