package com.ctw.workstation.team.boundary;

import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.control.TeamService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/teams")
public class TeamResource {

    //TODO mockar os comportamentos

    @Inject
    TeamService teamService;

    @GET
    public List<Team> findAll() {
        return teamService.findAllTeams();
    }

    @GET
    @Path("/{id}")
    public Team findById(@PathParam("id") UUID id) {
        return teamService.findById(id);
    }

    @POST
    public Response createTeam(Team team) {
        teamService.addTeam(team);
        return Response.status(Response.Status.CREATED).entity(team).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTeam(@PathParam("id") UUID id, Team updateTeam) {
        Team updatedTeam = teamService.updateTeam(id, updateTeam);
        return Response.ok().entity(updatedTeam).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTeam(@PathParam("id") UUID id) {
        teamService.deleteTeam(id);
        return Response.noContent().build();
    }
}
