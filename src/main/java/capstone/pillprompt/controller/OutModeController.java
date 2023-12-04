package capstone.pillprompt.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.dto.OutModeRequest;
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
    @PatchMapping("/dispose")
    public void disposeForOutMode(@RequestBody OutModeRequest request) {
        outModeService.disposeForOutMode(request.getTimes(), request.getQuantities());
    }
}
