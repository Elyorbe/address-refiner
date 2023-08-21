package me.elyor.addressrefiner.preprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Keeps only Korean characters after preprocessing
 * */
@Component
public class DefaultAddressPreprocessor implements AddressPreprocessor{

    private static final Pattern ROAD_NAME_PATTERN = Pattern.compile(
            "(?<road>"
                    + "[가-힣0-9 ]+길\\b|[가-힣0-9 ]+로\\b)"
    );

    @Override
    public String preprocess(String rawAddress) {
        rawAddress = rawAddress.replaceAll("[^가-힣0-9 ]", "");
        Matcher matcher = ROAD_NAME_PATTERN.matcher(rawAddress);
        String prepared = "";
        if(matcher.find()) {
            prepared = matcher.group(0);
        }
        return prepared;
    }

}
