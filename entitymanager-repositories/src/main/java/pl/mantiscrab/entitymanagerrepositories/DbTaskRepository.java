package pl.mantiscrab.entitymanagerrepositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
interface DbTaskRepository extends CrudRepository<Task, Long> {
    List<Task>findAllByStartTimeIsNullOrderByPriorityDesc();
    List<Task>findAllByCompletionTimeIsNotNullOrderByCompletionTimeDesc();
}
//
//    EntityManager entityManager;
//    private static long nextId = 1;
//
//
//    public DbTaskRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    public Task save(Task task) {
//        task.setId(nextId);
//        nextId++;0
//        entityManager.persist(task);
//        return task;
//    }
//
//    @Override
//    public Optional<Task> findById(Long id) {
//        return Optional.ofNullable(entityManager.find(Task.class, id));
//    }

