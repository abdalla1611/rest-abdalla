package controllers;

import Entity.*;
import data.ChoreData;
import data.HomeWorkData;
import data.PersonData;
import data.TaskData;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.PeopleRepository;
import java.net.URI;
import java.util.List;

@Path("/People")
@Produces({"application/json","text/pain"})
@Consumes({"application/json","text/pain"})
@Transactional
public class PeopleController {

    @Inject
    PeopleRepository repository;

    @GET
    @Produces("application/json")
    public Response findAll() {
        List<People> list = repository.findAll();
        return Response.ok().status(Response.Status.OK).entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id) {

        People p = repository.findById(id);
        if(p != null) {
            return Response.ok(p).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("A person with the id " + id + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();
    }

    @POST
    public Response create(PersonData person) {
        try {
            People p = new People(person.getName(), person.getEmail(), person.getFavoriteProgrammingLanguage(), 0);
            repository.create(p);
            return Response.created(
                    URI.create("/People/" + p.getId())).status(201).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("A person with email " + person.getEmail() + " already exists.")
                    .type(MediaType.TEXT_PLAIN).build();
        }
    }

    @PATCH
    @Path("/{id}")
    public Response update(@PathParam("id") String id, PersonData person) {

        try {
            People p = repository.findById(id);
            if(p == null){
                throw new Exception("id not found");
            }
            p.setName(person.getName());
            p.setEmail(person.getEmail());
            p.setFavoriteProgrammingLanguage(person.getFavoriteProgrammingLanguage());
            repository.update(p);
            return Response.ok(p, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("A person with the id " + id + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();
        }

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {

        People people = repository.findById(id);
        if(people != null){
            repository.delete(people);
            return Response.ok().build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("A person with the id " + id + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();
        }
    }

    @Path("/{id}/tasks/")
    @GET
    public Response GetTasks(@PathParam("id") String id, @QueryParam("status") String status) {
        if (repository.findById(id) == null){
            return Response.status(Response.Status.NOT_FOUND).entity("A person with the id " + id + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();
        }
            List<Task> list;
            if (status == null) {
                list = repository.getTasks(id);
            }
            else{
                list = repository.getTasksFilter(id, status);
            }
            return Response.ok().status(Response.Status.OK).entity(list).build();
    }
    @Path("/{id}/tasks/")
    @POST
    public Response addTask(@PathParam("id") String id , TaskData t) {
        String taskId ;
        People person = repository.findById(id);
        if (person != null){
            if(t instanceof ChoreData){
                ChoreData ch = (ChoreData)t ;
                Status s = ch.getStatus() ;
                if (s != null){
                    taskId = person.AddTask(new Chore(s,ch.getDescription(),ch.getSize()));
                    if(s == Status.Active){
                        person.IncrementActiveTaskCount();
                    }
                }
                else {
                    taskId = person.AddTask(new Chore(Status.Active,ch.getDescription(),ch.getSize()));
                    person.IncrementActiveTaskCount();
                }
            }
            else {
                HomeWorkData hw = (HomeWorkData) t;
                Status s = hw.getStatus();
                if (s != null) {
                    taskId = person.AddTask(new HomeWork(s,hw.getCourse(), hw.getDetails(), hw.getDueDate()));
                    if(s == Status.Active){
                        person.IncrementActiveTaskCount();
                    }
                } else {
                    taskId = person.AddTask(new HomeWork(Status.Active, hw.getCourse(), hw.getDetails(), hw.getDueDate()));
                    person.IncrementActiveTaskCount();
                }
            }

            if(taskId == null){
                return Response.status(Response.Status.BAD_REQUEST).entity("Required data fields are missing, data makes no sense, or data contains illegal values")
                        .type(MediaType.TEXT_PLAIN).build();
            }

            return Response.created(
                    URI.create("/People/" + id +"/tasks/"+taskId)).status(201).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("A person with the id " + id + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();

        }

    }

}
