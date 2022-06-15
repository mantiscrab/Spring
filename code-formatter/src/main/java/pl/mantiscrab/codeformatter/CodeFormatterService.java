package pl.mantiscrab.codeformatter;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.google.googlejavaformat.java.GoogleJavaFormatToolProvider;
import org.springframework.stereotype.Service;

@Service
public class CodeFormatterService {
    public String format(String code) throws FormatterException {
        String formattedCode = new Formatter().formatSource(code);
        return formattedCode;
    }
}
