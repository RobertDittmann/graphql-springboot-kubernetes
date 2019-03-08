package com.rdittmann.graphql.sample.GraphQGSample.repository.custom;

import com.rdittmann.graphql.sample.GraphQGSample.model.User;

public interface CustomUserRepository {

    User findUserBySurname(final String surname);

}
