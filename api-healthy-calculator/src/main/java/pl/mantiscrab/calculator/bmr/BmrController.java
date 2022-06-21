package pl.mantiscrab.calculator.bmr;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mantiscrab.calculator.exceptions.UnknownGenderException;
import pl.mantiscrab.calculator.exceptions.WeightHeightMassMustBePositive;

@RestController
@RequestMapping(
        path = "/api/bmr",
        produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.TEXT_PLAIN_VALUE
        }
)

public class BmrController {
    private final BmrService bmrService;

    public BmrController(BmrService bmrService) {
        this.bmrService = bmrService;
    }

    @GetMapping("/{gender}")
    ResponseEntity<BmrFullInfo> getBmrJson(
            @PathVariable String gender,
            @RequestParam double weight,
            @RequestParam double height,
            @RequestParam int age
    ) {
        try {
            BmrFullInfo bmr = bmrService.getBmr(gender, weight, height, age);
            return ResponseEntity.ok(bmr);
        } catch (UnknownGenderException | WeightHeightMassMustBePositive e) {
            return ResponseEntity
                    .badRequest()
                    .header("reason", e.getMessage())
                    .build();
        }
    }
}
