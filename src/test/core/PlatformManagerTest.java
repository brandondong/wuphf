package test.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import core.model.PlatformManager;
import facebook.core.FacebookPlatform;

public class PlatformManagerTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testPlatforms() {
		assertTrue(PlatformManager.instance().getAllPlatforms().contains(new FacebookPlatform()));
	}

	@Test
	public void testGetByLabel() {
		String label = new FacebookPlatform().getLabel();
		assertEquals(new FacebookPlatform(), PlatformManager.instance().getPlatformByLabel(label));
	}

	@Test
	public void testNoMatchingLabel() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("No platform found with label invalid label.");
		PlatformManager.instance().getPlatformByLabel("invalid label");
	}

}
