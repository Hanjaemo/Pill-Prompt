package capstone.pillprompt.repository;

import capstone.pillprompt.domain.Times;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TimesRepository {

    private final EntityManager em;

    public void save(Times times) {
        em.persist(times);
    }

    public List<Times> findAll() {
        Query query = em.createQuery("select t from Times t");
        return query.getResultList();
    }

    public Times findById(Long id) {
        return em.find(Times.class, id);
    }
}
