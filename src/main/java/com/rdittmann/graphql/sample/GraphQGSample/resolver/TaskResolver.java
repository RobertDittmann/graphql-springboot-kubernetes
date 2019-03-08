package com.rdittmann.graphql.sample.GraphQGSample.resolver;

import com.rdittmann.graphql.sample.GraphQGSample.model.Task;
import com.rdittmann.graphql.sample.GraphQGSample.model.User;
import com.rdittmann.graphql.sample.GraphQGSample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskResolver {

    private UserRepository userRepository;

    @Autowired
    public TaskResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(Task task) {
        return userRepository.findById(task.getId());
    }
}
