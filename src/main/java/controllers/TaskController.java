package controllers;

import Entity.People;
import Entity.Status;
import Entity.Task;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.TaskRepository;

import java.util.List;

@Produces({"application/json","text/plain"})
@Consumes({"application/json","text/pain"})
@Transactional
@Path("/tasks")
public class TaskController {
    @Inject
    TaskRepository repository ;
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id){

        Task t = repository.findById(id);
        if(t != null) {
            return Response.ok(t).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("A task with the id " + id + " does not exist.")
                .type(MediaType.TEXT_PLAIN).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {

        Task task = repository.findById(id);
        if(task != null){
            task.getPerson().DeleteTask(task);
            repository.delete(task);
            return Response.ok().build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("A task with the id " + id + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();
        }
    }


    @Path("/{id}/status")
    @GET
    public Response GetTasks(@PathParam("id") String id) {
        Task task = repository.findById(id) ;
        if (task == null){
            return Response.status(Response.Status.NOT_FOUND).entity("A task with the id " + id + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();
        }
        return Response.ok(task.getStatus()).status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("/{id}/status")
    @PUT
    public Response UpdateStatus(@PathParam("id") String id , String status){
        Task task = repository.findById(id) ;
        if (task == null){
            return Response.status(Response.Status.NOT_FOUND).entity("A task with the id " + id + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();
        }
        if(status.equalsIgnoreCase(Status.Active.toString())){
            task.setStatus(Status.Done);
            task.getPerson().decrementActiveTaskCount();
            return Response.ok().build();

        }
        if (status.equalsIgnoreCase(Status.Done.toString())) {
            task.setStatus(Status.Active);
            task.getPerson().IncrementActiveTaskCount();
            return Response.ok().build();
        }

        return Response.status(Response.Status.BAD_REQUEST).entity("value "+status+" is not a legal task status.").
                type(MediaType.TEXT_PLAIN).build();

    }
    @Path("/{id}/owner")
    @GET
    public Response GetTaskIdOwner(@PathParam("id") String id){
        Task task = repository.findById(id) ;
        if (task == null){
            return Response.status(Response.Status.NOT_FOUND).entity("A task with the id " + id + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();
        }

        return Response.ok(task.getPerson().getId()).status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
    }

    @Path("/{id}/owner")
    @PUT
    public Response setTaskOwner(@PathParam("id") String  id , String newOwnerId){
        Task task = repository.findById(id);
        if (task == null){
            return Response.status(Response.Status.NOT_FOUND).entity("A task with the id " + id + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();
        }
        People person = repository.findPersonId(newOwnerId);
        if(person == null){
            return Response.status(Response.Status.NOT_FOUND).entity("A task with the id " + newOwnerId + " does not exist.")
                    .type(MediaType.TEXT_PLAIN).build();
        }
        task.getPerson().DeleteTask(task);
        person.AddTask(task);
        return Response.status(204).build();
    }


}
