package capstone.pillprompt.service;

import capstone.pillprompt.domain.Pill;
import capstone.pillprompt.dto.PillDto;
import capstone.pillprompt.dto.response.PillResponse;
import capstone.pillprompt.exception.ErrorCode;
import capstone.pillprompt.exception.domain.PillException;
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

    public Long save(PillDto pillDto) {
        Pill pill = Pill.of(pillDto);
        return pillRepository.save(pill);
    }

    public void update(Long id, PillDto newPill) {
        Pill pill = getPillById(id);
        pill.update(newPill);
    }

    public void delete(Long id) {
        Pill pill = getPillById(id);
        pillRepository.delete(pill.getId());
    }

    private Pill getPillById(Long id) {
        Pill pill = pillRepository.findById(id);
        if (isNull(pill)) {
            throw new PillException(ErrorCode.NOT_FOUND_PILL);
        }
        return pill;
    }

    private boolean isNull(Pill pill) {
        return pill == null;
    }

    private static List<PillResponse> toResponses(List<Pill> pills) {
        return pills.stream()
                .map(PillResponse::of)
                .collect(Collectors.toList());
    }
}
