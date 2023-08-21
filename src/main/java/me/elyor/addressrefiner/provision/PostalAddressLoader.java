package me.elyor.addressrefiner.provision;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.extern.slf4j.Slf4j;
import me.elyor.addressrefiner.core.PostalAddress;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
@Component
public class PostalAddressLoader {

    private PathMatchingResourcePatternResolver resourceLoader;
    private PostalAddressIndexManager addressManager;

    public PostalAddressLoader(PostalAddressIndexManager addressManager) {
        this.addressManager = addressManager;
        this.resourceLoader = new PathMatchingResourcePatternResolver();

    }

    @PostConstruct
    protected void prepareData() throws IOException {
        Resource[] addressResources = resourceLoader.getResources("classpath:addressdb/*.csv");
        log.info("Preparing postal address indexes");
        for(Resource resource: addressResources) {
            prepareIndexes(resource.getInputStream());
        }
        log.info("Postal address indexing done");
    }

    private void prepareIndexes(InputStream inputStream) {
        CSVReader csvReader = csvReaderFromInputStream(inputStream);
        for (String[] fields : csvReader) {
            String provinceCity = fields[0];
            String cityCountyDistrict = fields[1];
            String township = fields[2];
            String road = fields[3];
            PostalAddress postalAddress =
                    new PostalAddress(provinceCity, cityCountyDistrict, township, road);
            addressManager.process(postalAddress);
        }
    }

    private CSVReader csvReaderFromInputStream(InputStream inputStream) {
        return new CSVReaderBuilder(new InputStreamReader(inputStream))
                .withSkipLines(1) //skip header
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .build();
    }

}
