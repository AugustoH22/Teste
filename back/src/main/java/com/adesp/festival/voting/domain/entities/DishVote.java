package com.adesp.festival.voting.domain.entities;

import com.adesp.festival.tokens.domain.entities.VotingToken;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity(
        name = "dish_votes"
)
public class DishVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            length = 64
    )
    private String name;

    @Column(
            name = "contact_number",
            nullable = false,
            length = 15
    )
    private String contactNumber;

    @Column(
            name = "presentation",
            nullable = false
    )
    private Float presentation;

    @Column(
            name = "treatment",
            nullable = false
    )
    private Float treatment;

    @Column(
            name = "creativity",
            nullable = false
    )
    private Float creativity;

    @Column(
            name = "originality",
            nullable = false
    )
    private Float originality;

    @Column(
            name = "flavor",
            nullable = false
    )
    private Float flavor;

    @OneToOne
    @JoinColumn(name = "token_id", referencedColumnName = "id")
    private VotingToken votingToken;

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

    public DishVote(Long id, String name, String contactNumber, Float presentation, Float treatment, Float creativity, Float originality, Float flavor, VotingToken votingToken, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.presentation = presentation;
        this.treatment = treatment;
        this.creativity = creativity;
        this.originality = originality;
        this.flavor = flavor;
        this.votingToken = votingToken;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public DishVote(String name, String contactNumber, Float presentation, Float treatment, Float creativity, Float originality, Float flavor, VotingToken votingToken) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.presentation = presentation;
        this.treatment = treatment;
        this.creativity = creativity;
        this.originality = originality;
        this.flavor = flavor;
        this.votingToken = votingToken;
    }

    public DishVote(Long id) {
        this.id = id;
    }

    public DishVote() {
    }

    @PrePersist
    public void prePersistDishVote(){
        this.isActive = true;
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPresentation() {
        return presentation;
    }

    public void setPresentation(Float presentation) {
        this.presentation = presentation;
    }

    public Float getTreatment() {
        return treatment;
    }

    public void setTreatment(Float treatment) {
        this.treatment = treatment;
    }

    public Float getCreativity() {
        return creativity;
    }

    public void setCreativity(Float creativity) {
        this.creativity = creativity;
    }

    public Float getOriginality() {
        return originality;
    }

    public void setOriginality(Float originality) {
        this.originality = originality;
    }

    public Float getFlavor() {
        return flavor;
    }

    public void setFlavor(Float flavor) {
        this.flavor = flavor;
    }

    public VotingToken getVotingToken() {
        return votingToken;
    }

    public void setVotingToken(VotingToken votingToken) {
        this.votingToken = votingToken;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
