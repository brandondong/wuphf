package test.core;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import core.schema.Field;
import core.schema.Fields;

public class FieldsTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	private final Field field1 = Field.builder("mock").create();

	private final Field field2 = Field.builder("mock2").description("mock description").create();

	private final Field field3 = Field.builder("mock3").id().create();

	private final Field field4 = Field.builder("mock4").id().create();

	@Test
	public void noIdFields() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Platform fields must contain exactly one id field, 0 found instead.");
		Fields.create(field1, field2);
	}

	@Test
	public void tooManyIdFields() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Platform fields must contain exactly one id field, 2 found instead.");
		Fields.create(field3, field4, field2);
	}

	@Test
	public void validFields() {
		Fields fields = Fields.create(field1, field2, field3);
		assertEquals(field3, fields.getIdField());
		assertEquals(field1, fields.getFieldByLabel("mock").get());
	}

}
