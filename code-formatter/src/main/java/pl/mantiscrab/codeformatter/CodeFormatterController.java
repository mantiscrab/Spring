package pl.mantiscrab.codeformatter;

import com.google.googlejavaformat.java.FormatterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CodeFormatterController {
    public CodeFormatterController(CodeFormatterService codeFormatterService) {
        this.codeFormatterService = codeFormatterService;
    }

    private final CodeFormatterService codeFormatterService;

    @PostMapping("/format")
    String format(@RequestParam String notFormattedCode, Model model) {
        try {
            String formattedCode = codeFormatterService.format(notFormattedCode);
            model.addAttribute("formattedCode", formattedCode);
            model.addAttribute("notFormattedCode", notFormattedCode);
            System.out.println(formattedCode);
            return "formattedCode";
        } catch (FormatterException e) {
            return "error";
        }
    }

}
