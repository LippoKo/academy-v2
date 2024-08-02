package com.ctw.workstation.rackasset.entity;

import com.ctw.workstation.rack.entity.Rack;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "T_RACK_ASSET")
public class RackAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ASSET_TAG", nullable = false)
    private String asset_tag;

    @Column(name = "RACK_ID", nullable = false)
    private UUID rack_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RACK_ID",insertable=false, updatable = false)
    private Rack rack;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAsset_tag() {
        return asset_tag;
    }

    public void setAsset_tag(String asset_tag) {
        this.asset_tag = asset_tag;
    }

    public UUID getRack_id() {
        return rack_id;
    }

    public void setRack_id(UUID rack_id) {
        this.rack_id = rack_id;
    }
}
