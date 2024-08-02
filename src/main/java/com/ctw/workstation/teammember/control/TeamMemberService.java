package com.ctw.workstation.teammember.control;

import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TeamMemberService {

    @Inject
    TeamMemberRepository teamMemberRepository;

    @Transactional
    public void addTeamMember(TeamMember teamMember) {
        teamMemberRepository.createTeamMember(teamMember);
    }

    public List<TeamMember> findAllTeamMembers() {
        return teamMemberRepository.getAllTeamMembers();
    }

    public TeamMember findById(UUID id) {
        return teamMemberRepository.getById(id);
    }

    @Transactional
    public TeamMember updateTeamMember(UUID id, TeamMember updatedTeamMember ) {
        TeamMember oldTeamMember = findById(id);
        oldTeamMember.setTeamId(updatedTeamMember.getTeamId());
        oldTeamMember.setCtwId(updatedTeamMember.getCtwId());
        oldTeamMember.setName(updatedTeamMember.getName());
        teamMemberRepository.updateTeamMember(oldTeamMember);
        return oldTeamMember;
    }

    @Transactional
    public void deleteTeamMember(UUID id) {
        teamMemberRepository.removeTeamMember(id);
    }
}
