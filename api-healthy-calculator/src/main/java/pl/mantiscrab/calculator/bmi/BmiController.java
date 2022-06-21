package pl.mantiscrab.calculator.bmi;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mantiscrab.calculator.exceptions.WeightHeightMustBePositiveException;

@RestController
@RequestMapping(path = "/api/bmi",
        produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.TEXT_PLAIN_VALUE
        })
public class BmiController {
    private final BmiService bmiService;

    public BmiController(BmiService bmiService) {
        this.bmiService = bmiService;
    }

    @GetMapping
    ResponseEntity<BmiFullInfo> getBmiJson(@RequestParam int weight, @RequestParam int height) {
        try {
            return ResponseEntity.ok(
                    bmiService.getBmi(weight, height)
            );
        } catch (WeightHeightMustBePositiveException e) {
            return ResponseEntity
                    .badRequest()
                    .header("reason", e.getMessage())
                    .build();
        }
    }
}
