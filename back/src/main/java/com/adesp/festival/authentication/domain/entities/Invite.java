package com.adesp.festival.authentication.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.Instant;

@Entity(
        name = "invites"
)
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "destination_email",
            nullable = false
    )
    private String destinationEmail;

    @Column(
            name = "invite_token",
            nullable = false
    )
    private String inviteToken;

    @Column(
            name = "expires_in",
            nullable = false
    )
    private Instant expiresIn;

    @Column(
            name = "is_active",
            nullable = false
    )
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(
            name = "created_by",
            referencedColumnName = "id"
    )
    private User createdBy;

    @CreationTimestamp
    @Column(
            name = "created_at",
            nullable = false
    )
    private Timestamp createdAt;

    public Invite(Long id, String destinationEmail, String inviteToken, Instant expiresIn, Boolean isActive, User createdBy) {
        this.id = id;
        this.destinationEmail = destinationEmail;
        this.inviteToken = inviteToken;
        this.expiresIn = expiresIn;
        this.isActive = isActive;
        this.createdBy = createdBy;
    }

    public Invite(String destinationEmail, String inviteToken, Instant expiresIn, Boolean isActive, User createdBy) {
        this.destinationEmail = destinationEmail;
        this.inviteToken = inviteToken;
        this.expiresIn = expiresIn;
        this.isActive = isActive;
        this.createdBy = createdBy;
    }

    public Invite(Long id) {
        this.id = id;
    }

    public Invite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInviteToken() {
        return inviteToken;
    }

    public void setInviteToken(String inviteToken) {
        this.inviteToken = inviteToken;
    }

    public Instant getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Instant expiresIn) {
        this.expiresIn = expiresIn;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDestinationEmail() {
        return destinationEmail;
    }

    public void setDestinationEmail(String destinationEmail) {
        this.destinationEmail = destinationEmail;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
