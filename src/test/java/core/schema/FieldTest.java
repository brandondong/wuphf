package core.schema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import core.schema.Field;
import core.schema.InputType;

public class FieldTest {

	@Test
	public void create() {
		Field field = Field.builder("mock").create();
		assertEquals("mock", field.getLabel());
		assertFalse(field.getDescription().isPresent());
		assertFalse(field.isIdField());
		assertEquals(InputType.TEXT, field.getType());
	}

	@Test
	public void createWithDescription() {
		Field field = Field.builder("mock").description("mock description").create();
		assertEquals("mock", field.getLabel());
		assertEquals("mock description", field.getDescription().get());
		assertFalse(field.isIdField());
	}

	@Test
	public void createIdField() {
		Field field = Field.builder("mock").id().type(InputType.PASSWORD).create();
		assertEquals("mock", field.getLabel());
		assertTrue(field.isIdField());
		assertEquals(InputType.PASSWORD, field.getType());
	}

	@Test
	public void testEquals() {
		Field field1 = Field.builder("mock1").create();
		Field field2 = Field.builder("mock2").create();
		Field field3 = Field.builder("mock1").description("description").create();
		assertFalse(field1.equals(field2));
		assertFalse(field2.equals(field3));
		assertEquals(field1, field3);
	}

}
