package pl.mantiscrab.entitymanagerrepositories;

import org.springframework.stereotype.Service;
import pl.mantiscrab.entitymanagerrepositories.dto.NewTaskDto;
import pl.mantiscrab.entitymanagerrepositories.dto.TaskDurationInfo;
import pl.mantiscrab.entitymanagerrepositories.dto.TaskFullInfoDto;
import pl.mantiscrab.entitymanagerrepositories.exceptions.TaskAlreadyCompletedException;
import pl.mantiscrab.entitymanagerrepositories.exceptions.TaskAlreadyStartedException;
import pl.mantiscrab.entitymanagerrepositories.exceptions.TaskNotFoundException;
import pl.mantiscrab.entitymanagerrepositories.exceptions.TaskNotStartedException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private static long nextId = 1;
    private final DbTaskRepository dbTaskRepository;

    public TaskService(DbTaskRepository dbTaskRepository) {
        this.dbTaskRepository = dbTaskRepository;
    }

    @Transactional
    public long addTask(NewTaskDto newTaskDto) {
        Task task = TaskMapper.mapToTask(newTaskDto);
        task.setId(nextId);
        nextId++;
        Task savedTask = dbTaskRepository.save(task);
        return savedTask.getId();
    }


    public List<TaskFullInfoDto> getNotStartedTasks() {
        return dbTaskRepository.findAllByStartTimeIsNullOrderByPriorityDesc().stream().map(TaskMapper::mapToTaskFullInfoDto).toList();
    }

    public List<TaskFullInfoDto> getCompletedTasks() {
        return dbTaskRepository.findAllByCompletionTimeIsNotNullOrderByCompletionTimeDesc().stream().map(TaskMapper::mapToTaskFullInfoDto).toList();
    }

    public Optional<TaskFullInfoDto> getTask(long taskId) {
        return dbTaskRepository.findById(taskId).map(TaskMapper::mapToTaskFullInfoDto);
    }

    @Transactional
    public TaskDurationInfo startTask(long taskId) {
        Task task = dbTaskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if (task.getStartTime() != null)
            throw new TaskAlreadyStartedException();
        task.setStartTime(LocalDateTime.now());
        return TaskMapper.mapToTaskDurationInfo(task);
    }

    @Transactional
    public TaskDurationInfo endTask(long taskId) {
        Task task = dbTaskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if (task.getStartTime() == null)
            throw new TaskNotStartedException();
        if (task.getCompletionTime() != null)
            throw new TaskAlreadyCompletedException();
        task.setCompletionTime(LocalDateTime.now());
        return TaskMapper.mapToTaskDurationInfo(task);
    }

    private static class TaskMapper {
        public static Task mapToTask(NewTaskDto newTaskDto) {
            return new Task(
                    newTaskDto.getTitle(),
                    newTaskDto.getDescription(),
                    newTaskDto.getPriority()
            );
        }

        public static TaskFullInfoDto mapToTaskFullInfoDto(Task task) {
            return new TaskFullInfoDto(task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getPriority(),
                    task.getStartTime(),
                    task.getCompletionTime());
        }

        public static TaskDurationInfo mapToTaskDurationInfo(Task task) {
            if (task.getCompletionTime() == null)
                return new TaskDurationInfo(task.getStartTime());
            else
                return new TaskDurationInfo(task.getStartTime(), task.getCompletionTime());
        }
    }
}
