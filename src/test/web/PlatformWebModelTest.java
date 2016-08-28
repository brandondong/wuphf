package test.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import test.core.MockPlatform;
import web.model.PlatformWebModel;

public class PlatformWebModelTest {

	@Test
	public void testConvert() {
		PlatformWebModel model = PlatformWebModel.createFrom(new MockPlatform());
		assertEquals(MockPlatform.LABEL, model.getLabel());
		assertEquals(MockPlatform.LOGO_LINK, model.getLogoImageLink());
	}

}
