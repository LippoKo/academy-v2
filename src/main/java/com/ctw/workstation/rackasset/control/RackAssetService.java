package com.ctw.workstation.rackasset.control;

import com.ctw.workstation.rackasset.entity.RackAsset;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RackAssetService {

    @Inject
    RackAssetRepository rackAssetRepository;

    @Transactional
    public RackAsset addRackAsset(RackAsset rackAsset) {
        if (rackAsset.getRack_id() == null) {
            rackAssetRepository.createRackAsset(rackAsset);
        } else {
            rackAsset = rackAssetRepository.getEntityManager().merge(rackAsset);
        }
        return rackAsset;
    }

    public List<RackAsset> findAllRackAssets() {
        return rackAssetRepository.getAllRackAssets();
    }

    public RackAsset findById(UUID id) {
        return rackAssetRepository.getById(id);
    }

    @Transactional
    public RackAsset updateRackAsset(UUID id, RackAsset updatedRackAsset ) {
        RackAsset oldRackAsset = findById(id);
        oldRackAsset.setAsset_tag(updatedRackAsset.getAsset_tag());
        oldRackAsset.setRack_id(updatedRackAsset.getRack_id());
        rackAssetRepository.updateRackAsset(oldRackAsset);
        return oldRackAsset;
    }

    @Transactional
    public void deleteRackAsset(UUID id) {
        rackAssetRepository.removeRackAsset(id);
    }


}
