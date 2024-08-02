package com.ctw.workstation.teammember.boundary;

import com.ctw.workstation.teammember.control.TeamMemberService;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/teammembers")
public class TeamMemberResource {

    @Inject
    TeamMemberService teamMemberService;

    @GET
    public List<TeamMember> findAll() {
        return teamMemberService.findAllTeamMembers();
    }

    @GET
    @Path("/{id}")
    public TeamMember findById(@PathParam("id") UUID id) {
        return teamMemberService.findById(id);
    }

    @POST
    @Transactional
    public Response createTeamMember(TeamMember teamMember) {
        if (teamMember.getCtwId() == null || teamMember.getTeamId() == null || teamMember.getName() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("CTW ID, Team ID and Name cannot be null").build();
        }
        teamMemberService.addTeamMember(teamMember);
        return Response.status(Response.Status.CREATED).entity(teamMember).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTeamMember(@PathParam("id") UUID id, TeamMember updateTeamMember) {
        TeamMember  updatedTeamMember = teamMemberService.updateTeamMember(id, updateTeamMember);
        return Response.ok().entity(updatedTeamMember).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteTeamMember(@PathParam("id") UUID id) {
        teamMemberService.deleteTeamMember(id);
        return Response.noContent().build();
    }
}
