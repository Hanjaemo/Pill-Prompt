package capstone.pillprompt.repository;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.Pill;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PillRepository {

    private final EntityManager em;

    public Long save(Pill pill) {
        em.persist(pill);
        return pill.getId();
    }

    public List<Pill> findAll() {
        return em.createQuery("select p from Pill p", Pill.class)
                .getResultList();
    }

    public List<Pill> findByTime(NameOfTime time) {
        return em.createQuery("select p from Pill p join p.times t where t = :timeName", Pill.class)
                .setParameter("timeName", time)
                .getResultList();
    }

    public Pill findById(Long id) {
        return em.find(Pill.class, id);
    }

    public void delete(Long id) {
        Pill pill = findById(id);
        em.remove(pill);
    }
}