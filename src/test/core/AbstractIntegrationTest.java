package test.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AbstractIntegrationTest {

	@Test
	public void testEquals() {
		assertTrue(new MockIntegration("id1").equals(new MockIntegration("id1")));
		assertFalse(new MockIntegration("id1").equals(new MockIntegration("id2")));
		assertFalse(new MockIntegration("id1").equals("not an integration"));
	}

}
