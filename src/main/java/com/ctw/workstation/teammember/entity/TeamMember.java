package com.ctw.workstation.teammember.entity;

import com.ctw.workstation.shared.EntityBase;
import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "T_TEAM_MEMBER")
public class TeamMember extends EntityBase {
    @Column(name = "TEAM_ID", nullable = false)
    private UUID teamId;

    @Column(name = "CTW_ID", unique = true, nullable = false)
    private String ctwId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", insertable=false, updatable = false)
    private Team team;

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public String getCtwId() {
        return ctwId;
    }

    public void setCtwId(String ctwId) {
        this.ctwId = ctwId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
