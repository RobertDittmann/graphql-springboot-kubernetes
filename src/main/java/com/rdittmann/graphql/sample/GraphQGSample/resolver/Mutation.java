package com.rdittmann.graphql.sample.GraphQGSample.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.rdittmann.graphql.sample.GraphQGSample.exceptions.UserNotFoundException;
import com.rdittmann.graphql.sample.GraphQGSample.model.Task;
import com.rdittmann.graphql.sample.GraphQGSample.model.TaskType;
import com.rdittmann.graphql.sample.GraphQGSample.model.User;
import com.rdittmann.graphql.sample.GraphQGSample.repository.TaskRepository;
import com.rdittmann.graphql.sample.GraphQGSample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public Mutation(final UserRepository userRepository, final TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public User addUser(final String name, final String surname) {
        final User user = new User(name, surname);
        userRepository.save(user);
        return user;
    }


    public Task addTask(final TaskType taskType, final String description, final Long userId) {
        final Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            final Task task = new Task(taskType, description, user.get());
            taskRepository.save(task);
            return task;
        }
        throw new UserNotFoundException("User with given ID was not found", userId);
    }

}