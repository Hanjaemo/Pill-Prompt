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
    public TakingTime findByName(@PathVariable final String name) {
        NameOfTime nameOfTime = NameOfTime.valueOf(name.toUpperCase());
        return timeService.findByName(nameOfTime);
    }

    @PatchMapping("/{name}/edit")
    public void updateMorning(@PathVariable final String name, @RequestBody final TimeDto time) {
        LocalTime localTime = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());
        NameOfTime nameOfTime = NameOfTime.valueOf(name.toUpperCase());
        timeService.update(nameOfTime, localTime);
    }
}
