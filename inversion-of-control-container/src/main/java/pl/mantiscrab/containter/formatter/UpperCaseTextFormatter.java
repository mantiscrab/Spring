package pl.mantiscrab.containter.formatter;

import org.springframework.stereotype.Service;


@Service
public class UpperCaseTextFormatter implements TextFormatter {

    @Override
    public String format(String text) {
        return text.toUpperCase();
    }
}
