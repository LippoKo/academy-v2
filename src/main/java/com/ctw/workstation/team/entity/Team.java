package com.ctw.workstation.team.entity;

import com.ctw.workstation.enums.DefaultLocation;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.shared.EntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "T_TEAM")
public class Team extends EntityBase {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRODUCT", nullable = false)
    private String product;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEFAULT_LOCATION", nullable = false)
    private DefaultLocation default_location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public DefaultLocation getDefault_location() {
        return default_location;
    }

    public void setDefault_location(String default_location) {
        if(default_location == null || default_location.isEmpty()) {
            throw new IllegalArgumentException("Default Location cannot be Null or Empty!");
        }
        this.default_location = DefaultLocation.fromString(default_location);
    }
}
