package capstone.pillprompt.service;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.Pill;
import capstone.pillprompt.dto.pill.PillDto;
import capstone.pillprompt.dto.pill.response.PillResponse;
import capstone.pillprompt.exception.ErrorCode;
import capstone.pillprompt.exception.domain.PillException;
import capstone.pillprompt.repository.PillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PillService {

    private final PillRepository pillRepository;

    @Transactional(readOnly = true)
    public List<PillResponse> findAll() {
        List<Pill> pills = pillRepository.findAll();
        return toResponses(pills);
    }

    public List<PillResponse> findByTime(NameOfTime time) {
        List<Pill> pills = getPillByTime(time);
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

    public void dispose(NameOfTime time) {
        List<Pill> pills = getPillByTime(time);
        for (Pill pill : pills) {
            pill.disposed();
        }
    }

    public void taken(NameOfTime time) {
        List<Pill> pills = getPillByTime(time);
        for (Pill pill : pills) {
            pill.taken(time);
        }
    }

    public void takeCancel(Long id, NameOfTime time) {
        Pill pill = pillRepository.findById(id);
        pill.takeCancel(time);
    }

    private Pill getPillById(Long id) {
        Pill pill = pillRepository.findById(id);
        if (isNull(pill)) {
            throw new PillException(ErrorCode.NOT_FOUND_PILL);
        }
        return pill;
    }

    private List<Pill> getPillByTime(NameOfTime time) {
        return pillRepository.findByTime(time);
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
