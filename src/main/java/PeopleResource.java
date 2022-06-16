import Entity.People;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/people")
public class PeopleResource {
    @PersistenceContext
    private EntityManager em ;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public People getPerson(@PathParam("id") String id) {
        return em.find(People.class,id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<People> GetAllPeople(){
        return em.createQuery("SELECT p FROM People p",People.class).getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void AddPerson(){
        return ;
    }


}
