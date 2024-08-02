package com.ctw.workstation.rack.entity;


import com.ctw.workstation.enums.DefaultLocation;
import com.ctw.workstation.enums.Status;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.shared.EntityBase;
import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "T_RACK")
public class Rack extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "SERIAL_NUMBER", unique = true, length = 20, nullable = false)
    private String serial_number;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;

    @Column(name = "TEAM_ID", nullable = false)
    private UUID team_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEFAULT_LOCATION", nullable = false)
    private DefaultLocation default_location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", insertable=false, updatable = false)
    private Team team;

    @OneToMany(mappedBy = "rack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RackAsset> rackAssets;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.fromString(status);
    }

    public UUID getTeam_id() {
        return team_id;
    }

    public void setTeam_id(UUID team_id) {
        this.team_id = team_id;
    }

    public DefaultLocation getDefault_location() {
        return default_location;
    }

    public void setDefault_location(String default_location) {
        this.default_location = DefaultLocation.fromString(default_location);
    }

    public List<RackAsset> getRackAssets() {
        return rackAssets;
    }

    public void setRackAssets(List<RackAsset> rackAssets) {
        this.rackAssets = rackAssets;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
