package com.ctw.workstation.rack.control;

import com.ctw.workstation.rack.entity.Rack;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RackRepository implements PanacheRepository<Rack> {

    public void createRack(Rack rack) {
        persist(rack);
    }

    public List<Rack> getAllRacks() {
        return listAll();
    }

    public Rack getById(UUID id) {
        return find("id", id).firstResult();
    }

    public void updateRack(Rack rack) {
        getEntityManager().merge(rack);
    }

    public void removeRack(UUID id) {
        Rack rack = getById(id);
        if (rack == null) {
            throw new NotFoundException();
        }
        delete(rack);
    }

}
