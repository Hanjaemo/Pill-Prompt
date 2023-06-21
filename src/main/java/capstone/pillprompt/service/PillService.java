package capstone.pillprompt.service;

import capstone.pillprompt.domain.Pill;
import capstone.pillprompt.dto.PillDto;
import capstone.pillprompt.dto.response.PillResponse;
import capstone.pillprompt.repository.PillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PillService {

    private final PillRepository pillRepository;

    public List<PillResponse> findAll() {
        List<Pill> pills = pillRepository.findAll();
        return toResponses(pills);
    }

    public PillResponse findById(Long id) {
        Pill pill = getPillById(id);
        return PillResponse.of(pill);
    }

    public void save(PillDto pillDto) {
        Pill pill = Pill.of(pillDto);
        pillRepository.save(pill);
    }

    public void update(Long id, PillDto pillDto) {
        Pill pill = getPillById(id);
        pill.update(pillDto);
    }

    public void delete(Long id) {
        pillRepository.delete(id);
    }

    private Pill getPillById(Long id) {
        Pill pill = pillRepository.findById(id);
        return pill;
    }

    private static List<PillResponse> toResponses(List<Pill> pills) {
        return pills.stream()
                .map(PillResponse::of)
                .collect(Collectors.toList());
    }
}
