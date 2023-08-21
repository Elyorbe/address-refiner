package me.elyor.addressrefiner.core;

import lombok.extern.slf4j.Slf4j;
import me.elyor.addressrefiner.preprocessor.AddressPreprocessor;

import me.elyor.addressrefiner.core.RefinedPostalAddress.RefinedPostalAddressBuilder;
import me.elyor.addressrefiner.provision.PostalAddressIndexManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PostalAddressRefiner {

    private static final int MIN_ROAD_LENGTH = 3;
    public static int MAX_ROAD_LENGTH;
    private AddressPreprocessor addressPreprocessor;

    public PostalAddressRefiner(AddressPreprocessor addressPreprocessor) {
        this.addressPreprocessor = addressPreprocessor;
    }

    public RefinedPostalAddress refine(String rawAddress) {
        RefinedPostalAddressBuilder builder = RefinedPostalAddress.builder();
        builder.rawAddress(rawAddress);
        String preprocessed = addressPreprocessor.preprocess(rawAddress);
        builder.preprocessed(preprocessed);
        builder.possibleRoadNames(generatePossibleRoads(preprocessed));
        return builder.build();
    }

    private List<String> generatePossibleRoads(String address) {
        address = address.trim().replaceAll(" +", "");
        List<String> result = new ArrayList<>();
        for(int i = address.length(); i >= 0; i--) {
            for(int j = i-1; j >= 0; j--) {
                int windowSize =  i-j;
                if(windowSize >= MIN_ROAD_LENGTH && windowSize <= MAX_ROAD_LENGTH) {
                    result.add(address.substring(j, i));
                }
            }
        }
        return result;
    }

}
