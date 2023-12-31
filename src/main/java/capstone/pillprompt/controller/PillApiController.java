package capstone.pillprompt.controller;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.Pill;
import capstone.pillprompt.dto.pill.PillDto;
import capstone.pillprompt.dto.pill.request.PillRequest;
import capstone.pillprompt.dto.pill.response.PillResponse;
import capstone.pillprompt.service.PillService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/pills")
@RequiredArgsConstructor
public class PillApiController {

    private final PillService pillService;

    @ResponseStatus(OK)
    @GetMapping
    @Operation(summary = "약 전체 조회", description = "현재 보관중인 모든 약 목록을 조회한다.")
    public List<PillResponse> findAll() {
        return pillService.findAll();
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    @Operation(summary = "약 단건 조회", description = "현재 보관중인 약들 중 해당 id의 약을 조회한다.")
    public PillResponse findOne(@PathVariable final Long id) {
        return pillService.findById(id);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    @Operation(summary = "약 등록", description = "새로운 약을 보관함에 등록한다.")
    public Long save(@RequestBody @Valid final PillRequest request) {
        return pillService.save(Pill.of(request));
    }

    @ResponseStatus(OK)
    @PatchMapping("/{id}")
    @Operation(summary = "약 수정", description = "현재 보관중인 약들 중 해당 id의 약 정보를 수정한다.")
    public void update(@PathVariable final Long id, @RequestBody @Valid final PillRequest request) {
        pillService.update(id, Pill.of(request));
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "약 삭제", description = "현재 보관중인 약들 중 해당 id의 약을 삭제한다.")
    public void delete(@PathVariable final Long id) {
        pillService.delete(id);
    }

    @ResponseStatus(OK)
    @GetMapping("/by-time/{time}")
    @Operation(summary = "복용 시간에 해당하는 약 전체 조회", description = "복용 시간(아침, 점심, 저녁)에 해당하는 약 목록을 조회한다.")
    public List<PillResponse> findByTime(@PathVariable String time) {
        NameOfTime nameOfTime = NameOfTime.valueOf(time.toUpperCase());
        return pillService.findByTime(nameOfTime);
    }

    @ResponseStatus(OK)
    @PatchMapping("/dispose/{time}")
    @Operation(summary = "약 배출", description = "각 복용 시간에 해당하는 약의 수량이 1씩 감소한다.")
    public void dispose(@PathVariable String time) {
        NameOfTime nameOfTime = NameOfTime.valueOf(time.toUpperCase());
        pillService.dispose(nameOfTime);
    }

    @ResponseStatus(OK)
    @PatchMapping("/take/{time}")
    @Operation(summary = "약 복용", description = "각 복용 시간에 해당하는 약의 복용 여부를 true 값으로 수정한다.")
    public void take(@PathVariable String time) {
        NameOfTime nameOfTime = NameOfTime.valueOf(time.toUpperCase());
        pillService.taken(nameOfTime);
    }

    @ResponseStatus(OK)
    @PatchMapping("/{id}/take-cancel/{time}")
    @Operation(summary = "약 복용 취소", description = "해당 id에 대한 약의 복용 여부를 false 값으로 수정한다.")
    public void takeCancel(@PathVariable Long id, @PathVariable String time) {
        NameOfTime nameOfTime = NameOfTime.valueOf(time.toUpperCase());
        pillService.takeCancel(id, nameOfTime);
    }
}