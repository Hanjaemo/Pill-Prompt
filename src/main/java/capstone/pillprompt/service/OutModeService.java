package capstone.pillprompt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import capstone.pillprompt.domain.Member;
import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.Pill;
import capstone.pillprompt.repository.MemberRepository;
import capstone.pillprompt.repository.PillRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OutModeService {

    private final MemberRepository memberRepository;
    private final PillRepository pillRepository;

    public boolean isOutModeActivated() {
        Member member = memberRepository.findById(1L).orElseThrow();
        return member.isOutModeActivate();
    }

    public boolean switchOutMode() {
        Member member = memberRepository.findById(1L).orElseThrow();
        return member.switchOutMode();
    }

    public void disposeForOutMode(List<NameOfTime> times, List<Integer> quantities) {
        for (int i = 0; i < times.size(); i++) {
            NameOfTime time = times.get(i);
            Integer quantity = quantities.get(i);
            List<Pill> pills = getPillByTime(time);
            for (Pill pill : pills) {
                pill.disposed(time, quantity);
            }
        }
    }

    private List<Pill> getPillByTime(NameOfTime time) {
        return pillRepository.findByTime(time);
    }
}
