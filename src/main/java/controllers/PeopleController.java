package controllers;

import Entity.People;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import repository.PeopleRepository;
import java.net.URI;
import java.util.List;

@Path("/Peoples")
@Produces("application/json")
@Consumes("application/json")
@Transactional
public class PeopleController {

    @Inject
    PeopleRepository repository;

    @GET
    public List<People> findAll() {
        return repository.findAll();
    }

    @GET
    @Path("/{id}")
    public People findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @POST
    public Response create(People people) {
        repository.create(people);
        return Response.created(
                URI.create("/Peoples/" + people.getId())
        ).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, People people) {
        repository.update(people);
        return Response.ok(people).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        People People = repository.findById(id);
        repository.delete(People);
        return Response.ok().build();
    }
}
