package me.elyor.addressrefiner;

import me.elyor.addressrefiner.core.PostalAddressRefiner;
import me.elyor.addressrefiner.core.RefinedPostalAddress;
import me.elyor.addressrefiner.core.RoadNameChecker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoadNameExtractor {

    private RoadNameChecker roadNameChecker;
    private PostalAddressRefiner refiner;

    public RoadNameExtractor(RoadNameChecker roadNameChecker, PostalAddressRefiner refiner) {
        this.roadNameChecker = roadNameChecker;
        this.refiner = refiner;
    }

    public List<String> extractRoadNames(String rawAddress) {
        RefinedPostalAddress address = refiner.refine(rawAddress);
        List<String> possibleRoadNames = address.getPossibleRoadNames();
        List<String> validRoadNames = new ArrayList<>();
        for(String roadName : possibleRoadNames) {
            if(roadNameChecker.isValid(roadName)){
                validRoadNames.add(roadName);
            }
        }
        return validRoadNames;
    }
}
