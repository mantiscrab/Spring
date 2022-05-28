package pl.mantiscrab.entitymanagerrepositories;

import java.util.Optional;

interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
}
