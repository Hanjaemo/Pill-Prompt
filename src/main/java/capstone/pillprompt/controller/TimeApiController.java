package capstone.pillprompt.controller;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.TakingTime;
import capstone.pillprompt.dto.TimeDto;
import capstone.pillprompt.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/taking-time")
@RequiredArgsConstructor
public class TimeApiController {

    private final TimeService timeService;

    @ResponseStatus(OK)
    @GetMapping("/{name}")
    @Operation(summary = "복용 시간 이름이 가리키는 대한 시, 분, 초 조회", description = "각 복용 시간이 몇시, 몇분, 몇초를 가리키는지 조회한다.")
    public TakingTime findByName(@PathVariable final String name) {
        NameOfTime nameOfTime = NameOfTime.valueOf(name.toUpperCase());
        return timeService.findByName(nameOfTime);
    }

    @ResponseStatus(OK)
    @PatchMapping("/{name}/edit")
    @Operation(summary = "복용 시간이 가리키는 시, 분, 초 수정", description = "각 복용 시간이 가리키는 시, 분, 초를 수정한다.")
    public void updateMorning(@PathVariable final String name, @RequestBody final TimeDto time) {
        LocalTime localTime = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());
        NameOfTime nameOfTime = NameOfTime.valueOf(name.toUpperCase());
        timeService.update(nameOfTime, localTime);
    }
}
