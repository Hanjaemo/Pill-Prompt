package capstone.pillprompt.controller;

import capstone.pillprompt.dto.PillDto;
import capstone.pillprompt.dto.request.PillRequest;
import capstone.pillprompt.dto.response.PillResponse;
import capstone.pillprompt.service.PillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pills")
@RequiredArgsConstructor
public class PillApiController {

    private final PillService pillService;

    @GetMapping
    public List<PillResponse> findAll() {
        return pillService.findAll();
    }

    @GetMapping("/{id}")
    public PillResponse findOne(@PathVariable Long id) {
        return pillService.findById(id);
    }

    @PostMapping
    public Long save(@RequestBody PillRequest request) {
        return pillService.save(PillDto.of(request));
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PillRequest request) {
        pillService.update(id, PillDto.of(request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pillService.delete(id);
    }
}
