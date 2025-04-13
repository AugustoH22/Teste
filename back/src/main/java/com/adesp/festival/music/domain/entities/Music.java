package com.adesp.festival.music.domain.entities;

import com.adesp.festival.artist.domain.entities.Artist;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity(name = "music")
@EntityListeners(AuditingEntityListener.class)
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "interpreter_id",
                referencedColumnName = "id")
    private Artist interpreter;

    @ManyToOne
    @JoinColumn(name = "composer_id",
                referencedColumnName = "id")
    private Artist composer;

    @Column(
            name = "title",
            nullable = false,
            length = 64
    )
    private String title;

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

    public Music(Long id, Artist interpreter, Artist composer, String title, Long createdBy, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.interpreter = interpreter;
        this.composer = composer;
        this.title = title;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Music(Artist interpreter, Artist composer, String title, Long createdBy, Boolean isActive, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.interpreter = interpreter;
        this.composer = composer;
        this.title = title;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Music(Long id) {
        this.id = id;
    }

    public Music() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(Artist interpreter) {
        this.interpreter = interpreter;
    }

    public Artist getComposer() {
        return composer;
    }

    public void setComposer(Artist composer) {
        this.composer = composer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
