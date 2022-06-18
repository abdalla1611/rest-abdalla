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
public class TaskRepository {
    @Inject
    @PersistenceContext
    EntityManager em ;
    public void create(Task task) {
        em.persist(task);
    }

    public Task findById(String id) {
        return em.find(Task.class,id);
    }

    public People findPersonId(String id) {
        return em.find(People.class,id);
    }

    public void update(Task task) {
        em.merge(task);
    }

    public void delete(Task task) {
        em.remove(task);
    }

    public List<Task> findAll() {
        return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }
}

