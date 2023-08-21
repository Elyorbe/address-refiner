package me.elyor.addressrefiner.core;

import me.elyor.addressrefiner.provision.PostalAddressIndexManager;
import org.springframework.stereotype.Component;

@Component
public class IndexedRoadNameChecker implements RoadNameChecker {

    private PostalAddressIndexManager indexManager;

    public IndexedRoadNameChecker(PostalAddressIndexManager indexManager) {
        this.indexManager = indexManager;
    }

    @Override
    public boolean isValid(String roadName) {
        return indexManager.isValidRoad(roadName);
    }
}
