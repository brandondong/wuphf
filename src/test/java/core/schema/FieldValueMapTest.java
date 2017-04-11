package core.schema;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import core.schema.Field;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class FieldValueMapTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	private final Field field1 = Field.builder("mock").create();

	private final Field field2 = Field.builder("mock2").description("mock description").create();

	private final Field field3 = Field.builder("mock3").id().create();

	private final Fields fields = Fields.create(field1, field2, field3);

	@Test
	public void testIncorrectSize() {
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage("Value must be set for mock field.");
		FieldValueMap.builder(fields).create();
	}

	@Test
	public void testFieldDoesntBelong() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("No matching platform field found with label mock4.");
		FieldValueMap.builder(fields).setField("mock", "1").setField("mock4", "4");
	}

	@Test
	public void testAddEmptyValue() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Value for mock field cannot be empty.");
		FieldValueMap.builder(fields).setField("mock", "");
	}

	@Test
	public void testValid() {
		FieldValueMap map = FieldValueMap.builder(fields).setField("mock", "1").setField("mock2", "2")
				.setField("mock3", "3").create();
		assertEquals("1", map.getValueForField(field1));
		assertEquals("2", map.getValueForField(field2));
		assertEquals("3", map.getValueForField(field3));
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Field mock4 not found in platform fields.");
		map.getValueForField(Field.builder("mock4").create());
	}

}
