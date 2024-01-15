package com.microservice.microservicegym.dao.jpa;

import com.microservice.microservicegym.model.Trainer;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JpaDaoTrainerImpl extends JpaDaoImpl<Trainer> {
    @Override
    public Optional<Trainer> get(int id) {
        return Optional.of(getEntityManager().find(Trainer.class, id));
    }

    @Override
    public List<Trainer> getAll() {
        return getEntityManager().createQuery("FROM Trainer", Trainer.class).getResultList();
    }

    @Override
    public Trainer save(Trainer trainer) {
        String realPassword = trainer.getUser().getPassword();
        executeTransaction(entityManager -> {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            trainer.getUser().setPassword(passwordEncoder.encode(realPassword));
            entityManager.persist(trainer);
        });
        getEntityManager().detach(trainer);
        trainer.getUser().setPassword(realPassword);
        return trainer;
    }

    @Override
    public Trainer update(int id, Trainer trainer) {
        executeTransaction(entityManager -> entityManager.merge(trainer));
        return trainer;
    }

    @Override
    public Optional<Trainer> delete(int id) {
        Optional<Trainer> foundTrainer = get(id);
        foundTrainer.ifPresent(trainer -> executeTransaction(entityManager -> entityManager.remove(trainer)));
        return foundTrainer;
    }

    public Optional<Trainer> getByUsername(String username) {
        try {
            Query query = getEntityManager().createQuery("FROM Trainer t WHERE t.user.username = :username");
            query.setParameter("username", username);
            return Optional.of((Trainer) query.getSingleResult());

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
