package com.ctw.workstation.team.control;

import com.ctw.workstation.team.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {

    public void createTeam(Team team) {
        persist(team);
    }

    public List<Team> getAllTeams() {
        return listAll();
    }

    public Team getById(UUID id) {
        return find("id", id).firstResult();
    }

    public void updateTeam(Team team) {
        getEntityManager().merge(team);
    }

    public void removeTeam(UUID id) {
        Team team = getById(id);
        delete(team);
    }

}
