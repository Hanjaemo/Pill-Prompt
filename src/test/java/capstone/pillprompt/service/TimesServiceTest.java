package capstone.pillprompt.service;

import capstone.pillprompt.domain.Times;
import capstone.pillprompt.repository.TimesRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static capstone.pillprompt.domain.NameOfTimes.DINNER;
import static capstone.pillprompt.domain.NameOfTimes.MORNING;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class TimesServiceTest {

    @Autowired
    TimesService timesService;

    @Autowired
    TimesRepository timesRepository;

    @Test
    @DisplayName("Times 클래스 저장")
    void save() throws Exception {
        // given
        Times times = Times.builder()
                .nameOfTimes(MORNING)
                .time(LocalTime.of(8, 00, 01))
                .build();

        // when
        timesRepository.save(times);
        Times findTimes = timesRepository.findById(times.getId());

        // then
        assertThat(findTimes.getNameOfTimes()).isEqualTo(MORNING);
    }

    @Test
    @DisplayName("Times 전체 조회")
    void findAll() throws Exception {
        // given
        Times morning = Times.builder()
                .nameOfTimes(MORNING)
                .time(LocalTime.of(8, 00, 01))
                .build();

        Times dinner = Times.builder()
                .nameOfTimes(DINNER)
                .time(LocalTime.of(18, 30, 00))
                .build();

        timesRepository.save(morning);
        timesRepository.save(dinner);

        // when
        List<Times> times = timesRepository.findAll();

        // then
        assertThat(times.size()).isEqualTo(2);
        assertThat(times).contains(morning, dinner);
    }
}