package capstone.pillprompt.controller;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.dto.PillDto;
import capstone.pillprompt.dto.request.PillRequest;
import capstone.pillprompt.dto.response.PillResponse;
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
    @GetMapping("/take/{time}")
    @Operation(summary = "복용 시간에 해당하는 약 전체 조회", description = "복용 시간(아침, 점심, 저녁)에 해당하는 약 목록을 조회한다.")
    public List<PillResponse> findByTime(@PathVariable String time) {
        NameOfTime nameOfTime = NameOfTime.valueOf(time.toUpperCase());
        return pillService.findByTime(nameOfTime);
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
        return pillService.save(PillDto.of(request));
    }

    @ResponseStatus(OK)
    @PatchMapping("/{id}")
    @Operation(summary = "약 수정", description = "현재 보관중인 약들 중 해당 id의 약 정보를 수정한다.")
    public void update(@PathVariable final Long id, @RequestBody @Valid final PillRequest request) {
        pillService.update(id, PillDto.of(request));
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "약 삭제", description = "현재 보관중인 약들 중 해당 id의 약을 삭제한다.")
    public void delete(@PathVariable final Long id) {
        pillService.delete(id);
    }
}