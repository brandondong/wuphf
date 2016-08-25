package test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Test;

import core.model.Integration;
import core.schema.Field;
import test.core.MockIntegration;
import test.core.MockPlatform;
import web.model.IntegrationWebModel;
import web.schema.FieldWebModel;

public class IntegrationWebModelTest {

	@Test
	public void testConvert() {
		Integration integration = MockIntegration.create("id");
		IntegrationWebModel model = IntegrationWebModel.createFrom(integration);
		assertEquals("id", model.getId());
		assertEquals(MockPlatform.LABEL, model.getPlatform().getLabel());
		assertNull(model.getLabel());
		assertFalse(model.getIntegrationLabel().isPresent());
		Map<FieldWebModel, String> map = model.getValueMap();
		for (FieldWebModel field : map.keySet()) {
			Field platformField = integration.getPlatform().getFields().getFieldByLabel(field.getLabel()).get();
			assertEquals(integration.getFieldValueMap().getValueForField(platformField), map.get(field));
		}
	}

}
