package com.ctw.workstation.team.control;


import com.ctw.workstation.team.entity.Team;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TeamService{

    @Inject
    TeamRepository teamRepository;


    @Transactional
    public void addTeam(Team team) {
        teamRepository.createTeam(team);
    }

    public List<Team> findAllTeams() {
        return teamRepository.getAllTeams();
    }

    public Team findById(UUID id) {
        return teamRepository.getById(id);
    }

    @Transactional
    public Team updateTeam(UUID id, Team updatedTeam ) {
        Team oldTeam = findById(id);
        oldTeam.setName(updatedTeam.getName());
        oldTeam.setProduct(updatedTeam.getProduct());
        oldTeam.setDefault_location(updatedTeam.getDefault_location().toString());
        teamRepository.updateTeam(oldTeam);
        return oldTeam;
    }

    @Transactional
    public void deleteTeam(UUID id) {
        Team team = findById(id);
        if (team == null) {
            throw new NotFoundException("Id not found.");
        }
        teamRepository.removeTeam(id);
    }
}