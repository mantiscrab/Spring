package pl.mantiscrab.codeformatter;

import com.google.googlejavaformat.java.FormatterException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CodeFormatterApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeFormatterApplication.class, args);
    }
}
