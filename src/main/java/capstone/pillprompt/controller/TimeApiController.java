package capstone.pillprompt.controller;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.TakingTime;
import capstone.pillprompt.dto.TimeDto;
import capstone.pillprompt.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/taking-time")
@RequiredArgsConstructor
public class TimeApiController {

    private final TimeService timeService;

    @GetMapping("/{name}")
    public TakingTime findByName(@PathVariable NameOfTime name) {
        return timeService.findByName(name);
    }

    @PatchMapping("/morning")
    public void updateMorning(@RequestBody TimeDto time) {
        LocalTime localTime = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());
        timeService.update(NameOfTime.MORNING, localTime);
    }

}
