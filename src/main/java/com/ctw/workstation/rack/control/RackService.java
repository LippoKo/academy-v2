package com.ctw.workstation.rack.control;

import com.ctw.workstation.rack.entity.Rack;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RackService {

    @Inject
    RackRepository rackRepository;

    @Transactional
    public void addRack(Rack rack) {
        rackRepository.createRack(rack);
    }

    public List<Rack> findAllRacks() {
        return rackRepository.getAllRacks();
    }

    public Rack findById(UUID id) {
        return rackRepository.getById(id);
    }

    @Transactional
    public Rack updateRack(UUID id, Rack updatedRack ) {
        Rack oldRack = findById(id);
        oldRack.setSerial_number(updatedRack.getSerial_number());
        oldRack.setStatus(updatedRack.getStatus().toString());
        oldRack.setTeam_id(updatedRack.getTeam_id());
        oldRack.setDefault_location(updatedRack.getDefault_location().toString());
        rackRepository.updateRack(oldRack);
        return oldRack;
    }

    @Transactional
    public void deleteRack(UUID id) {
        rackRepository.removeRack(id);
    }
}
