package capstone.pillprompt.repository;

import capstone.pillprompt.domain.Pill;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PillRepository {

    private final EntityManager em;

    public Long save(Pill pill) {
        em.persist(pill);
        return pill.getId();
    }

    public Optional<Pill> findById(Long id) {
        return Optional.ofNullable(em.find(Pill.class, id));
    }

    public List<Pill> findAll() {
        return em.createQuery("select p from Pill p", Pill.class)
                .getResultList();
    }

    public void delete(Long id) {
        Optional<Pill> pill = findById(id);
        em.remove(pill);
    }
}
