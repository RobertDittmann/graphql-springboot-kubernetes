package com.rdittmann.graphql.sample.GraphQGSample.repository.impl;

import com.rdittmann.graphql.sample.GraphQGSample.exceptions.UserNotFoundException;
import com.rdittmann.graphql.sample.GraphQGSample.model.User;
import com.rdittmann.graphql.sample.GraphQGSample.repository.custom.CustomUserRepository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager em;


    @Override
    public User findUserBySurname(String surname) {

        List<User> users = em.createQuery("select u from User u where u.surname = :surname", User.class)
                .setParameter("surname", surname).getResultList();
        if (CollectionUtils.isEmpty(users) || users.size() != 1) {
            throw new UserNotFoundException("User with given surname does not exist", surname);
        }
        return users.get(0);
    }
}
