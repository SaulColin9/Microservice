package com.microservice.microservicegym.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Trainer extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "specialization", referencedColumnName = "id")
    private TrainingType specialization;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Trainer() {
    }

    public Trainer(TrainingType specialization) {
        this.specialization = specialization;
    }

    public Trainer(TrainingType specialization, User user) {
        this.specialization = specialization;
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public TrainingType getSpecialization() {
        return specialization;
    }

    public Trainer setSpecialization(TrainingType specialization) {
        this.specialization = specialization;
        return this;
    }


    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", specialization=" + specialization +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return getId() == trainer.getId() && getSpecialization() == trainer.getSpecialization() && Objects.equals(getUser(), trainer.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpecialization(), getUser());
    }
}
