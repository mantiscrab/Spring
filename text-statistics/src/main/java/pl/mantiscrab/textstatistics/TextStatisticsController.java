package pl.mantiscrab.textstatistics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TextStatisticsController {
    private final TextStatisticsService textStatisticsService;

    public TextStatisticsController(TextStatisticsService textStatisticsService) {
        this.textStatisticsService = textStatisticsService;
    }

    @PostMapping("/stats")
    String getStatistics(
            @RequestParam(required = false)String text,
            @RequestParam(required = false)String [] statistics,
            Model model) {
        if(text.isEmpty()) {
            return "error";
        } else {
            TextStatisticsDto textStatisticsDto = textStatisticsService.getStatistics(text, statistics);
            model.addAttribute(textStatisticsDto);
            return "statistics";
        }
    }
}
