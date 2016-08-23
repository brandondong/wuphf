package test.core;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.collect.ImmutableMap;

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
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Integration properties do not match platform fields.");
		FieldValueMap.createWith(fields, Collections.emptyMap());
	}

	@Test
	public void testFieldDoesntBelong() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("No matching platform field found with label mock4.");
		FieldValueMap.createWith(fields, ImmutableMap.of("mock", "1", "mock2", "2", "mock4", "4"));
	}

	@Test
	public void testValid() {
		FieldValueMap map = FieldValueMap.createWith(fields, ImmutableMap.of("mock", "1", "mock2", "2", "mock3", "3"));
		assertEquals("1", map.getValueForField(field1));
		assertEquals("2", map.getValueForField(field2));
		assertEquals("3", map.getValueForField(field3));
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Field mock4 not found in platform fields.");
		map.getValueForField(Field.builder("mock4").create());
	}

}
