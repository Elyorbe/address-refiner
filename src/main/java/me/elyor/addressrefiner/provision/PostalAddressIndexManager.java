package me.elyor.addressrefiner.provision;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import me.elyor.addressrefiner.core.PostalAddress;
import me.elyor.addressrefiner.core.PostalAddressRefiner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class PostalAddressIndexManager {

    private Map<String, List<PostalAddress>> roadIndex;

    public PostalAddressIndexManager() {
        roadIndex = new HashMap<>();
    }

    public void process(PostalAddress postalAddress) {
        String key = postalAddress.getRoad();
        updateIndex(roadIndex, postalAddress, key);
        updateRoadLengthBoundary(key);
    }

    public boolean isValidRoad(String roadName) {
        return roadIndex.containsKey(roadName);
    }

    private void updateIndex(Map<String, List<PostalAddress>> index,
                             PostalAddress postalAddress , String key) {
        if(StringUtils.isEmpty(key))
            return;

        if(!index.containsKey(key))
            index.put(key, new ArrayList<>());
        List<PostalAddress> value = index.get(key);
        value.add(postalAddress);
        index.put(key, value);
    }

    private void updateRoadLengthBoundary(String roadName) {
        int roadLength = roadName.length();
        PostalAddressRefiner.MAX_ROAD_LENGTH =
                Math.max(PostalAddressRefiner.MAX_ROAD_LENGTH, roadLength);
    }

}
