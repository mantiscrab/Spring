package pl.mantiscrab.entitymanagerrepositories.dto;

import java.time.LocalDateTime;

public class TaskFullInfoDto {

    private Long id;
    private String title;
    private String description;
    private int priority;
    private LocalDateTime startTime;
    private LocalDateTime completionTime;

    public TaskFullInfoDto(Long id, String title, String description, int priority, LocalDateTime startTime, LocalDateTime completionTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.startTime = startTime;
        this.completionTime = completionTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    @Override
    public String toString() {
        String start = startTime == null ? "Nierozpoczęte" : "Rozpoczęte " + startTime;
        String end = completionTime == null ? null : "Zakończone " + completionTime;
        String timeInfo;
        if (completionTime == null) {
            timeInfo = start;
        } else {
            timeInfo = String.format("%s / %s", start, end);
        }
        return String.format(
                "Zadanie %d: %s (%s). Priorytet: %d. %s",
                id, title, description, priority, timeInfo
        );
    }
}
