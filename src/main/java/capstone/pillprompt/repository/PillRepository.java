package capstone.pillprompt.repository;

import capstone.pillprompt.domain.Pill;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PillRepository {

    private final EntityManager em;

    public void save(Pill pill) {
        em.persist(pill);
    }

    public Pill findById(Long id) {
        return em.find(Pill.class, id);
    }

    public List<Pill> findAll() {
        return em.createQuery("select p from Pill p", Pill.class)
                .getResultList();
    }

    public void delete(Long id) {
        Pill pill = em.find(Pill.class, id);
        em.remove(pill);
    }
}
