package core.model;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import core.model.Platform;
import core.model.PlatformManager;
import facebook.core.FacebookPlatform;

public class PlatformManagerTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testGetByLabel() {
		String label = new FacebookPlatform().getLabel();
		Platform facebookPlatform = PlatformManager.instance().getPlatformByLabel(label);
		assertTrue(facebookPlatform instanceof FacebookPlatform);
	}

	@Test
	public void testNoMatchingLabel() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("No platform found with label invalid label.");
		PlatformManager.instance().getPlatformByLabel("invalid label");
	}

}
