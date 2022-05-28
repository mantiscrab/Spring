package pl.mantiscrab.entitymanagerrepositories;

import org.springframework.stereotype.Controller;
import pl.mantiscrab.entitymanagerrepositories.dto.NewTaskDto;
import pl.mantiscrab.entitymanagerrepositories.dto.TaskDurationInfo;
import pl.mantiscrab.entitymanagerrepositories.dto.TaskFullInfoDto;
import pl.mantiscrab.entitymanagerrepositories.exceptions.TaskAlreadyCompletedException;
import pl.mantiscrab.entitymanagerrepositories.exceptions.TaskAlreadyStartedException;
import pl.mantiscrab.entitymanagerrepositories.exceptions.TaskNotFoundException;
import pl.mantiscrab.entitymanagerrepositories.exceptions.TaskNotStartedException;

import java.time.Duration;
import java.util.Optional;
import java.util.Scanner;

@Controller
class TaskController {
    private final TaskService taskService;
    private final Scanner scanner;

    public TaskController(TaskService taskService, Scanner scanner) {
        this.taskService = taskService;
        this.scanner = scanner;
    }

    public void loop() {
        Option option;
        do {
            printOptions();
            option = chooseOption();
            evaluateOption(option);
        } while (option != Option.EXIT);
    }

    private void printOptions() {
        System.out.println("\nWybierz opcję:");
        for (Option option : Option.values()) {
            System.out.println(option);
        }
    }

    private Option chooseOption() {
        int optionNumber = scanner.nextInt();
        scanner.nextLine();
        return Option.fromInt(optionNumber);
    }

    private void evaluateOption(Option option) {
        switch (option) {
            case ADD -> addTask();
            case PRINT_NOT_STARTED_TASKS -> printNotStartedTasks();
            case PRINT_COMPLETED_TASKS -> printCompletedTasks();
            case PRINT_SINGLE -> printTask();
            case START_TASK -> startTask();
            case END_TASK -> endTask();
            case EXIT -> exit();
        }
    }

    private void addTask() {
        System.out.println("Podaj tytuł zadania:");
        String title = scanner.nextLine();
        System.out.println("Opis zadania:");
        String description = scanner.nextLine();
        System.out.println("Priorytet (wyższa liczba = wyższy priorytet):");
        int priority = scanner.nextInt();
        scanner.nextLine();
        NewTaskDto newTaskDto = new NewTaskDto(title, description, priority);
        long savedTaskId = taskService.addTask(newTaskDto);
        System.out.println("Zadanie zapisane z identyfikatorem " + savedTaskId);
    }

    private void printNotStartedTasks() {
        taskService.getNotStartedTasks().forEach(System.out::println);
    }

    private void printCompletedTasks() {
        taskService.getCompletedTasks().forEach(System.out::println);
    }

    private void printTask() {
        System.out.println("Podaj identyfikator zadania:");
        long taskId = scanner.nextLong();
        scanner.nextLine();
        Optional<TaskFullInfoDto> taskToPrint = taskService.getTask(taskId);
        taskToPrint.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Brak wpisu o takim id"));
    }

    private void startTask() {
        System.out.println("Podaj identyfikator zadania:");
        long taskId = scanner.nextLong();
        scanner.nextLine();
        try {
            TaskDurationInfo taskDurationInfo = taskService.startTask(taskId);
            System.out.println(taskDurationInfo);
        } catch (TaskNotFoundException e) {
            System.out.println("Nie znaleziono zadania o takim id");
        } catch (TaskAlreadyStartedException e) {
            System.out.println("Zadanie jest już wystartowane");
        }
    }

    private void endTask() {
        System.out.println("Podaj identyfikator zadania:");
        long taskId = scanner.nextLong();
        scanner.nextLine();
        try {
            TaskDurationInfo taskDurationInfo = taskService.endTask(taskId);
            System.out.println(taskDurationInfo);
        } catch (TaskNotFoundException e) {
            System.out.println("Nie znaleziono zadania o takim id");
        } catch (TaskNotStartedException e) {
            System.out.println("Zadanie nie zostało wystartowane");
        } catch (TaskAlreadyCompletedException e) {
            System.out.println("Zadanie zostało zakończone już wcześniej");
        }
    }

    private void exit() {
        System.out.println("Koniec programu!");
    }

    private enum Option {
        ADD(1, "Dodaj nowe zadanie"),
        PRINT_SINGLE(2, "Wyświetl zadanie"),
        PRINT_NOT_STARTED_TASKS(3, "Wyświetl nierozpoczęte zadania"),
        PRINT_COMPLETED_TASKS(4, "Wyświetl zakońćzone zadania"),
        START_TASK(5, "Rozpocznij zadanie"),
        END_TASK(6, "Zakończ zadanie"),
        EXIT(7, "Koniec programu");

        private final int number;
        private final String name;

        Option(int number, String name) {
            this.number = number;
            this.name = name;
        }

        static Option fromInt(int option) {
            return values()[option - 1];
        }

        @Override
        public String toString() {
            return number + " - " + name;
        }
    }
}
