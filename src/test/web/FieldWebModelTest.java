package test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import core.schema.Field;
import web.schema.FieldWebModel;

public class FieldWebModelTest {

	@Test
	public void testConvert() {
		FieldWebModel field = FieldWebModel.createFrom(Field.builder("mock").id().create());
		assertEquals("mock", field.getLabel());
		assertNull(field.getDescription());
	}

	@Test
	public void testConvertWithDescription() {
		FieldWebModel field = FieldWebModel.createFrom(Field.builder("mock").description("description").create());
		assertEquals("mock", field.getLabel());
		assertEquals("description", field.getDescription());
	}

}
