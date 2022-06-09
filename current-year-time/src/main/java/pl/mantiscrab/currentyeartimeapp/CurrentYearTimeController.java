package pl.mantiscrab.currentyeartimeapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class CurrentYearTimeController {
    @RequestMapping("/current-time")
    @ResponseBody
    String currentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    @RequestMapping("/current-year")
    @ResponseBody
    String currentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
    }
}
