package com.rdittmann.graphql.sample.GraphQGSample.repository;

import com.rdittmann.graphql.sample.GraphQGSample.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
