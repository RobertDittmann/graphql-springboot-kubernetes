package com.rdittmann.graphql.sample.GraphQGSample;

import com.rdittmann.graphql.sample.GraphQGSample.exceptions.GraphQLErrorAdapter;
import com.rdittmann.graphql.sample.GraphQGSample.model.Task;
import com.rdittmann.graphql.sample.GraphQGSample.model.TaskType;
import com.rdittmann.graphql.sample.GraphQGSample.model.User;
import com.rdittmann.graphql.sample.GraphQGSample.repository.TaskRepository;
import com.rdittmann.graphql.sample.GraphQGSample.repository.UserRepository;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GraphQgSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQgSampleApplication.class, args);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public CommandLineRunner fillH2withDummyData(final UserRepository userRepository, final TaskRepository taskRepository) {
        return (args) -> {
            userRepository.save(new User("Jan", "Kowalski"));
            userRepository.save(new User("Adam", "Nowak"));
            taskRepository.save(new Task(TaskType.HOME, "Wash dishes", userRepository.findUserBySurname("Kowalski")));
            taskRepository.save(new Task(TaskType.JOB, "Push changes", userRepository.findUserBySurname("Kowalski")));

        };
    }
}
