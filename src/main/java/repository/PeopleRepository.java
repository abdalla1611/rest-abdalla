package repository;

import Entity.People;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
@Transactional
public class PeopleRepository {

    @Inject
    EntityManager em ;
    public void create(People people) {
        em.persist(people);
    }

    public People findById(Long id) {
        return em.find(People.class, id);
    }

    public void update(People people) {
        em.merge(people);
    }

    public void delete(People people) {
        em.remove(people);
    }

    public List<People> findAll() {
        return em.createQuery("SELECT p FROM People p", People.class).getResultList();
    }
}
