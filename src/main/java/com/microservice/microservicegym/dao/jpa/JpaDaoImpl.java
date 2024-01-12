package com.microservice.microservicegym.dao.jpa;

import com.microservice.microservicegym.dao.Dao;
import com.microservice.microservicegym.model.EntityModel;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Service
public abstract class JpaDaoImpl<T extends EntityModel> implements Dao<T> {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void executeTransaction(Consumer<EntityManager> action) {
        action.accept(entityManager);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
