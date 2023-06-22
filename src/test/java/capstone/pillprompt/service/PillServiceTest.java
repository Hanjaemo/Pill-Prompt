package capstone.pillprompt.service;

import capstone.pillprompt.domain.Dinner;
import capstone.pillprompt.domain.Morning;
import capstone.pillprompt.domain.TakingTime;
import capstone.pillprompt.dto.PillDto;
import capstone.pillprompt.dto.response.PillResponse;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PillServiceTest {

    @Autowired
    PillService pillService;

    @Test
    @DisplayName("")
    void save() {
        // given
        TakingTime time1 = new Morning("아침", LocalTime.of(8, 00));
        TakingTime time2 = new Dinner("저녁", LocalTime.of(18, 30));

        List<TakingTime> times = new ArrayList<>();
        times.add(time1);
        times.add(time2);

        PillDto pill = new PillDto("pillA", 10, times, false);

        // when
        Long id = pillService.save(pill);
        PillResponse pillResponse = pillService.findById(id);

        // then
        assertThat(pillResponse.getName()).isEqualTo("pillA");
        assertThat(pillResponse.getQuantity()).isEqualTo(10);
        assertThat(pillResponse.getTimes()).contains(time1, time2);
    }

}