
import Entity.People;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class Main extends Application {
    public static void main(String[] args) {
        People p1 = new People();
        p1.setName("abdalla");
        p1.setFavoriteProgrammingLanguage("Java");
        p1.setEmail("abdalla@post.bgu.ac.il");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(p1);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}