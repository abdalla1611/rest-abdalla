package repository;

import Entity.People;
import Entity.Task;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
@Transactional
public class PeopleRepository {

    @Inject
    @PersistenceContext
    EntityManager em ;
    public void create(People people) {
        em.persist(people);
    }

    public People findById(String id) {
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
    public List<Task> getTasks(String id){
        return em.createNamedQuery("Tasks.byPerson", Task.class)
                .setParameter("ownerId", id)
                .getResultList();
    }
    public List<Task> getTasksFilter(String id , String status){
        return em.createNamedQuery("TasksFilter.byPerson", Task.class)
                .setParameter("ownerId", id).setParameter("status", status)
                .getResultList();
    }

}
