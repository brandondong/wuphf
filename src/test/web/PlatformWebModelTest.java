package test.web;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import core.schema.Field;
import test.core.MockPlatform;
import web.model.PlatformWebModel;
import web.schema.FieldWebModel;

public class PlatformWebModelTest {

	@Test
	public void testConvert() {
		PlatformWebModel model = PlatformWebModel.createFrom(new MockPlatform());
		assertEquals(MockPlatform.LABEL, model.getLabel());
		assertEquals(MockPlatform.LOGO_LINK, model.getLogo());
		List<Field> fields = new MockPlatform().getUserFields().stream().collect(toList());
		List<FieldWebModel> webFields = model.getFields();
		assertEquals(fields.size(), webFields.size());
		for (int i = 0; i < fields.size(); i++) {
			assertEquals(fields.get(i).getLabel(), webFields.get(i).getLabel());
		}
	}

}
