package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.control.RackService;
import com.ctw.workstation.rack.entity.Rack;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/racks")
public class RackResource {

    @Inject
    RackService rackService;

    @GET
    public List<Rack> findAllRacks() {
        return rackService.findAllRacks();
    }

    @GET
    @Path("/{id}")
    public Rack findById(@PathParam("id") UUID id) {
        return rackService.findById(id);
    }

    @POST
    @Transactional
    public Response createRack(Rack rack) {
        rackService.addRack(rack);
        return Response.status(Response.Status.CREATED).entity(rack).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRack(@PathParam("id") UUID id, Rack updateRack) {
        Rack  updatedRack = rackService.updateRack(id, updateRack);
        return Response.ok().entity(updatedRack).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteRack(@PathParam("id") UUID id) {
        rackService.deleteRack(id);
        return Response.noContent().build();
    }

}
