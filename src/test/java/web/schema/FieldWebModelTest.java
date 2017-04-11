package web.schema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import core.schema.Field;
import core.schema.InputType;
import web.schema.FieldWebModel;

public class FieldWebModelTest {

	@Test
	public void testConvert() {
		FieldWebModel field = FieldWebModel.createFrom(Field.builder("mock").id().create());
		assertEquals("mock", field.getLabel());
		assertNull(field.getDescription());
		assertTrue(field.isIdField());
		assertEquals(InputType.TEXT, field.getType());
	}

	@Test
	public void testConvertWithDescription() {
		FieldWebModel field = FieldWebModel
				.createFrom(Field.builder("mock").description("description").type(InputType.EMAIL).create());
		assertEquals("mock", field.getLabel());
		assertEquals("description", field.getDescription());
		assertFalse(field.isIdField());
		assertEquals(InputType.EMAIL, field.getType());
	}

}
