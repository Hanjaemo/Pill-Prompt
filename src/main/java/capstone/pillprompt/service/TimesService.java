package capstone.pillprompt.service;

import capstone.pillprompt.domain.Times;
import capstone.pillprompt.repository.TimesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TimesService {

    private final TimesRepository timesRepository;

    public void saveTimes(Times times) {
        timesRepository.save(times);
    }

    public List<Times> findAll() {
        return timesRepository.findAll();
    }

    public Times findById(Long id) {
        return timesRepository.findById(id);
    }
}

