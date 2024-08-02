package com.ctw.workstation.team.control;

import com.ctw.workstation.team.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class TeamServiceTest {

    private TeamRepository teamRepository;
    private TeamService teamService;

    @BeforeEach
    public void setUp() {
        teamRepository = Mockito.mock(TeamRepository.class);
        teamService = new TeamService();
        teamService.teamRepository = teamRepository;
    }

    @Test
    public void findAllTeamsReturnsEmpty() {
        when(teamRepository.getAllTeams()).thenReturn(List.of());

        List<Team> result = teamService.findAllTeams();

        assertEquals(0, result.size());
    }

    @Test
    public void validateFindById() {
        UUID id = UUID.randomUUID();
        Team expectedTeam = new Team();
        expectedTeam.setId(id);
        when(teamRepository.getById(id)).thenReturn(expectedTeam);

        Team result = teamService.findById(id);

        assertEquals(expectedTeam, result);
    }

    @Test
    public void validadeFindByIdNotNull() {
        UUID id = UUID.randomUUID();
        when(teamRepository.getById(id)).thenReturn(null);

        Team result = teamService.findById(id);

        assertNull(result);
    }
}