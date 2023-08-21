package me.elyor.addressrefiner.core;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RefinedPostalAddress extends PostalAddress {

    private String rawAddress;
    private String preprocessed;
    private List<String> possibleRoadNames;

    @Builder
    public RefinedPostalAddress(String provinceCity, String cityCountyDistrict,
                                String township, String road, String rawAddress,
                                String preprocessed, List<String> possibleRoadNames) {
        super(provinceCity, cityCountyDistrict, township, road);
        this.rawAddress = rawAddress;
        this.preprocessed = preprocessed;
        this.possibleRoadNames = possibleRoadNames;
    }
}
