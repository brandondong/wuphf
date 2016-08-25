package test.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import core.model.Integration;

public class AbstractIntegrationTest {

	@Test
	public void testEquals() {
		assertTrue(MockIntegration.create("id1").equals(MockIntegration.create("id1")));
		assertFalse(MockIntegration.create("id1").equals(MockIntegration.create("id2")));
		assertFalse(MockIntegration.create("id1").equals("not an integration"));
	}

	@Test
	public void testDefaultValues() {
		Integration integration = MockIntegration.create("id");
		assertEquals("id", integration.getId());
		assertEquals(MockIntegration.DEFAULT_USERNAME,
				integration.getFieldValueMap().getValueForField(MockPlatform.USERNAME_FIELD));
		assertEquals(MockIntegration.DEFAULT_PASSWORD,
				integration.getFieldValueMap().getValueForField(MockPlatform.PASSWORD_FIELD));
	}

}
