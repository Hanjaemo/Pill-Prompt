package capstone.pillprompt.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.service.OutModeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/out-mode")
@RequiredArgsConstructor
public class OutModeController {

    private final OutModeService outModeService;

    @ResponseStatus(OK)
    @GetMapping
    public boolean isOutModeActivated() {
        return outModeService.isOutModeActivated();
    }

    @ResponseStatus(OK)
    @PatchMapping
    public boolean switchOutMode() {
        return outModeService.switchOutMode();
    }

    @ResponseStatus(OK)
    @PatchMapping("/dispose/{time}")
    public void disposeForOutMode(@PathVariable String time, @RequestParam("quantity") int quantityForDispose) {
        NameOfTime nameOfTime = NameOfTime.valueOf(time.toUpperCase());
        outModeService.disposeForOutMode(nameOfTime, quantityForDispose);
    }
}
