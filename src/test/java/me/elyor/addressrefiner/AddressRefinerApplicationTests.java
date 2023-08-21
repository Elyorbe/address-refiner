package me.elyor.addressrefiner;

import me.elyor.addressrefiner.core.RoadNameChecker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressRefinerApplicationTests {

	@Autowired
	private RoadNameChecker roadNameChecker;


	@Test
	void checkCorrectIndexing() {
		assertTrue(roadNameChecker.isValid("백현로"));
		assertTrue(roadNameChecker.isValid("공릉로"));
		assertTrue(roadNameChecker.isValid("보광로"));
	}

}
