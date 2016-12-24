package test.web;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import core.model.Platform;
import core.schema.Field;
import test.core.MockPlatform;
import web.model.PlatformWebModel;
import web.schema.FieldWebModel;

public class PlatformWebModelTest {

	@Test
	public void testConvert() {
		Platform platform = new MockPlatform();
		PlatformWebModel model = PlatformWebModel.createFrom(platform);
		assertEquals(MockPlatform.LABEL, model.getLabel());
		assertEquals(MockPlatform.LOGO_LINK, model.getLogo());
		List<Field> fields = platform.getUserFields().stream().collect(toList());
		List<Field> receiverFields = platform.getReceiverFields().stream().collect(toList());
		List<FieldWebModel> webFields = model.getUserFields();
		List<FieldWebModel> webReceiverFields = model.getReceiverFields();
		assertEquals(fields.size(), webFields.size());
		assertEquals(receiverFields.size(), webReceiverFields.size());
		for (int i = 0; i < fields.size(); i++) {
			assertEquals(fields.get(i).getLabel(), webFields.get(i).getLabel());
		}
		for (int i = 0; i < receiverFields.size(); i++) {
			assertEquals(receiverFields.get(i).getLabel(), webReceiverFields.get(i).getLabel());
		}
	}

}
