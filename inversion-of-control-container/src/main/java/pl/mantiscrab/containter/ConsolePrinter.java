package pl.mantiscrab.containter;

import org.springframework.stereotype.Service;
import pl.mantiscrab.containter.formatter.TextFormatter;
import pl.mantiscrab.containter.formatter.UpperCaseTextFormatter;

@Service
public class ConsolePrinter {
    private final TextFormatter textFormatter;

    public ConsolePrinter(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }

    public void print(String text) {
        System.out.println(textFormatter.format(text));
    }
}
