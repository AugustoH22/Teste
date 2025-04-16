package com.adesp.festival.tokens.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity(
        name = "voting_tokens"
)
public class VotingToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "voting_token",
            nullable = false,
            unique = true
    )
    private String votingToken;

    @Column(
            name = "cpf",
            nullable = false
    )
    private String cpf;

    @OneToOne
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private VotingTokenBatch batch;

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

    public VotingToken(Long id, String votingToken, String cpf, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.votingToken = votingToken;
        this.cpf = cpf;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public VotingToken(String votingToken, String cpf, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.votingToken = votingToken;
        this.cpf = cpf;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public VotingToken(String votingToken, VotingTokenBatch batch) {
        this.votingToken = votingToken;
        this.batch = batch;
    }

    public VotingToken(String votingToken) {
        this.votingToken = votingToken;
    }

    public VotingToken(Long id) {
        this.id = id;
    }

    public VotingToken() {
    }

    @PrePersist
    public void prePersistVotingToken(){
        this.isActive = true;
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVotingToken() {
        return votingToken;
    }

    public void setVotingToken(String votingToken) {
        this.votingToken = votingToken;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public VotingTokenBatch getBatch() {
        return batch;
    }

    public void setBatch(VotingTokenBatch batch) {
        this.batch = batch;
    }
}
