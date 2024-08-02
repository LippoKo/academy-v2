package com.ctw.workstation.teammember.control;

import com.ctw.workstation.teammember.entity.TeamMember;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TeamMemberRepository implements PanacheRepository<TeamMember> {

    public void createTeamMember(TeamMember teamMember) {
        persist(teamMember);
    }

    public List<TeamMember> getAllTeamMembers() {
        return listAll();
    }

    public TeamMember getById(UUID id) {
        return find("id", id).firstResult();
    }

    public void updateTeamMember(TeamMember teamMember) {
        getEntityManager().merge(teamMember);
    }

    public void removeTeamMember(UUID id) {
        TeamMember teamMember = getById(id);
        if (teamMember == null) {
            throw new NotFoundException();
        }
        delete(teamMember);
    }
}
