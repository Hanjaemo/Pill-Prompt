package capstone.pillprompt.service;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.TakingTime;
import capstone.pillprompt.repository.TimeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeService {

    private final TimeRepository timeRepository;

    public void update(NameOfTime name, LocalTime newTime) {
        TakingTime takingTime = timeRepository.findByName(name);
        TakingTime updateTime = takingTime.update(newTime);
        timeRepository.save(updateTime);
    }

    public TakingTime findByName(NameOfTime name) {
        return timeRepository.findByName(name);
    }
}
