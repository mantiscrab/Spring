package pl.mantiscrab.testdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NamesApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NamesApp.class, args);
        NameRepository nameRepository = context.getBean(NameRepository.class);
        nameRepository.findTopTenNames().forEach(System.out::println);
    }
}
