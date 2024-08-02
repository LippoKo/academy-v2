package com.ctw.workstation.rackasset.boundary;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rackasset.control.RackAssetService;
import com.ctw.workstation.rackasset.entity.RackAsset;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/rackassets")
public class RackAssetResource {

    @Inject
    RackAssetService rackAssetService;

    @GET
    public List<RackAsset> findAllRacks() {
        return rackAssetService.findAllRackAssets();
    }

    @GET
    @Path("/{id}")
    public RackAsset findById(@PathParam("id") UUID id) {
        return rackAssetService.findById(id);
    }

    @POST
    @Transactional
    public Response createRack(RackAsset rackAsset) {
        RackAsset newRackAsset = rackAssetService.addRackAsset(rackAsset);
        return Response.status(Response.Status.CREATED).entity(newRackAsset).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRack(@PathParam("id") UUID id, RackAsset updateRackAsset) {
        RackAsset updatedRack = rackAssetService.updateRackAsset(id, updateRackAsset);
        return Response.ok().entity(updatedRack).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteRack(@PathParam("id") UUID id) {
        rackAssetService.deleteRackAsset(id);
        return Response.noContent().build();
    }
}
