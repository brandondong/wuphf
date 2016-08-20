package test.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AbstractPlatformTest {

	private class Mock2Platform extends MockPlatform {

	}

	@Test
	public void testEquals() {
		assertTrue(new MockPlatform().equals(new MockPlatform()));
		assertFalse(new Mock2Platform().equals(new MockPlatform()));
		assertFalse(new MockPlatform().equals(new Mock2Platform()));
		assertFalse(new MockPlatform().equals("not a platform"));
	}

}
