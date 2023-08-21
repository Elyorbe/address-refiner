package me.elyor.addressrefiner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoadNameExtractorTests {

    @Autowired
    RoadNameExtractor roadNameExtractor;

    @Test
    void whenRawAddressReturnRoadAddress() {
        String first = "성남, 분당 백 현 로 265, 푸른마을 아파트로 보내주세요!!";
        List<String> roadNames = roadNameExtractor.extractRoadNames(first);
        assertEquals(1, roadNames.size());
        assertEquals(roadNames.get(0), "백현로");

        String second = "마포구 도화-2길 코끼리분식";
        roadNames =  roadNameExtractor.extractRoadNames(second);
        assertEquals(1, roadNames.size());
        assertEquals(roadNames.get(0), "도화2길");

        String third = "용산구 보광로  31 길";
        roadNames = roadNameExtractor.extractRoadNames(third);
        assertEquals(2, roadNames.size());
        assertEquals("보광로31길", roadNames.get(0));
        assertEquals("보광로", roadNames.get(1));

    }
}