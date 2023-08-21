package me.elyor.addressrefiner.preprocessor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class DefaultAddressPreprocessorTests {

    private DefaultAddressPreprocessor preprocessor = new DefaultAddressPreprocessor();

    @Test
    void removeNonKoreanCharactersAndKeepOnlyRoadWhenPreprocess() {
        String given = "remomveme마포구 도화-2길 코끼리분식test";
        String expected = "마포구 도화2길";
        assertEquals(expected, preprocessor.preprocess(given));
    }

}