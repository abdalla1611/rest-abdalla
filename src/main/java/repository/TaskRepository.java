package repository;

import Entity.Task;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
@Transactional
public class TaskRepository{
    @Inject
    EntityManager em ;
    public void create(Task task) {
        em.persist(task);
    }

    public Task findById(Long id) {
        return em.find(Task.class, id);
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

