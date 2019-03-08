package com.rdittmann.graphql.sample.GraphQGSample.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.rdittmann.graphql.sample.GraphQGSample.model.Task;
import com.rdittmann.graphql.sample.GraphQGSample.model.User;
import com.rdittmann.graphql.sample.GraphQGSample.repository.TaskRepository;
import com.rdittmann.graphql.sample.GraphQGSample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public Query(final UserRepository userRepository, final TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserBySurname(final String surname) {
        return userRepository.findUserBySurname(surname);
    }

    public Iterable<Task> findAllTasks() {
        return taskRepository.findAll();
    }
}