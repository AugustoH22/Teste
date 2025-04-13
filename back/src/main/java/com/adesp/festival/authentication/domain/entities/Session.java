package com.adesp.festival.authentication.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.Instant;

@Entity(
        name = "sessions"
)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "refresh_token",
            nullable = false,
            unique = true
    )
    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(
            name = "expiration_date",
            nullable = false
    )
    private Instant expirationDate;

    @Column(
            name = "is_expired",
            nullable = false
    )
    private Boolean isExpired;

    @Column(
            name = "is_deleted",
            nullable = false
    )
    private Boolean isDeleted;

    @CreationTimestamp
    @Column(
            name = "created_at",
            nullable = false
    )
    private Timestamp createdAt;

    public Session(Long id, String refreshToken, User user, Instant expirationDate, Boolean isExpired, Boolean isDeleted) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.user = user;
        this.expirationDate = expirationDate;
        this.isExpired = isExpired;
        this.isDeleted = isDeleted;
    }

    public Session(String refreshToken, User user, Instant expirationDate, Boolean isExpired, Boolean isDeleted) {
        this.refreshToken = refreshToken;
        this.user = user;
        this.expirationDate = expirationDate;
        this.isExpired = isExpired;
        this.isDeleted = isDeleted;
    }

    public Session(Long id) {
        this.id = id;
    }

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
