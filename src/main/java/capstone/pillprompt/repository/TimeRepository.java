package capstone.pillprompt.repository;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.TakingTime;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TimeRepository {

    private final EntityManager em;

    public void save(TakingTime time) {
        em.persist(time);
    }

    public TakingTime findByName(NameOfTime name) {
        return em.find(TakingTime.class, name);
    }
}
