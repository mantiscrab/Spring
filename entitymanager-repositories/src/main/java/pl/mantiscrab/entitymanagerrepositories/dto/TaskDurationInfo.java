package pl.mantiscrab.entitymanagerrepositories.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskDurationInfo {
    private LocalDateTime startTime;
    private LocalDateTime completionTime;
    private Duration duration;

    public TaskDurationInfo(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public TaskDurationInfo(LocalDateTime startTime, LocalDateTime completionTime) {
        this.startTime = startTime;
        this.completionTime = completionTime;
        this.duration = Duration.between(startTime, completionTime);
    }

    @Override
    public String toString() {
        if(completionTime == null)
            return "Czas rozpoczęcia: " + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Czas rozpoczęcia: " + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append("Czas zakończenia: " + completionTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append("Czas trwania zadania: " + formattedDuration(duration));
            return stringBuilder.toString();
        }
    }

    private String formattedDuration(Duration duration) {
        return String.format("%d:%d:%d", duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart());
    }
}
