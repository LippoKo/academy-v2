package com.ctw.workstation.rackasset.control;

import com.ctw.workstation.rackasset.entity.RackAsset;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RackAssetRepository implements PanacheRepository<RackAsset> {

    public void createRackAsset(RackAsset rackAsset) {
        persist(rackAsset);
    }

    public List<RackAsset> getAllRackAssets() {
        return listAll();
    }

    public RackAsset getById(UUID id) {
        return find("id", id).firstResult();
    }

    public void updateRackAsset(RackAsset rackAsset) {
        getEntityManager().merge(rackAsset);
    }

    public void removeRackAsset(UUID id) {
        RackAsset rackAsset = getById(id);
        if (rackAsset == null) {
            throw new NotFoundException();
        }
        delete(rackAsset);
    }
}
