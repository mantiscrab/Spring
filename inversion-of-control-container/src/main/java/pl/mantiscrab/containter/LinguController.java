package pl.mantiscrab.containter;

import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

@Controller
class LinguController {
    private static final int TEST_SIZE = 10;
    private final EntryRepository entryRepository;

    private final FileService fileService;
    private final Scanner scanner;
    private final ConsolePrinter consolePrinter;

    public LinguController(EntryRepository entryRepository, FileService fileService, Scanner scanner, ConsolePrinter consolePrinter) {
        this.entryRepository = entryRepository;
        this.fileService = fileService;
        this.scanner = scanner;
        this.consolePrinter = consolePrinter;
    }

    void mainLoop() {
        consolePrinter.print("Witaj w aplikacji LinguApp");
        Option option = null;
        do {
            printMenu();
            try {
                option = chooseOption();
                executeOption(option);
            } catch (IllegalArgumentException e) {
                consolePrinter.print(e.getMessage());
            }
        } while (option != Option.EXIT);
    }

    private Option chooseOption() {
        int option = scanner.nextInt();
        scanner.nextLine();
        return Option.fromInt(option);
    }

    private void executeOption(Option option) {
        switch (option) {
            case ADD_ENTRY -> addEntry();
            case START_TEST -> startTest();
            case EXIT -> close();
        }
    }

    private void startTest() {
        if (entryRepository.isEmpty()) {
            consolePrinter.print("Dodaj przynajmniej jedną frazę do bazy.");
            return;
        }
        final int testSize = Math.min(entryRepository.size(), TEST_SIZE);
        Set<Entry> randomEntries = entryRepository.getRandomEntries(testSize);
        int score = 0;
        for (Entry entry : randomEntries) {
            System.out.printf("Podaj tłumaczenie dla :\"%s\"\n", entry.getOriginal());
            String translation = scanner.nextLine();
            if (entry.getTranslation().equalsIgnoreCase(translation)) {
                consolePrinter.print("Odpowiedź poprawna");
                score++;
            } else {
                consolePrinter.print("Odpowiedź niepoprawna - " + entry.getTranslation());
            }
        }
        consolePrinter.print(String.format("Twój wynik: %d/%d\n", score, testSize));
    }

    private void addEntry() {
        consolePrinter.print("Podaj oryginalną frazę");
        String original = scanner.nextLine();
        consolePrinter.print("Podaj tłumaczenie");
        String translation = scanner.nextLine();
        Entry entry = new Entry(original, translation);
        entryRepository.add(entry);
    }

    private void close() {
        try {
            fileService.saveEntries(entryRepository.getAll());
            consolePrinter.print("Zapisano stan aplikacji");
        } catch (IOException e) {
            consolePrinter.print("Nie udało się zapisać zmian");
        }
        consolePrinter.print("Bye Bye!");
    }

    private void printMenu() {
        consolePrinter.print("Wybierz opcję:");
        for (Option option : Option.values()) {
            consolePrinter.print(option.toString());
        }
    }

    private enum Option {
        ADD_ENTRY(1, "Dodaj tekst z tłumaczeniem"),
        START_TEST(2, "Rozpocznij test"),
        EXIT(3, "Koniec programu");

        private final int optionNumber;
        private final String description;

        Option(int optionNumber, String description) {
            this.optionNumber = optionNumber;
            this.description = description;
        }

        static Option fromInt(int option) {
            if (option < 0 || option > values().length) {
                throw new IllegalArgumentException("Opcja o takim numerze nie istnieje");
            }
            return values()[option - 1];
        }

        @Override
        public String toString() {
            return String.format("%d - %s", optionNumber, description);
        }
    }
}
