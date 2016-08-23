package test.core;

import core.model.AbstractPlatform;
import core.schema.Field;
import core.schema.Fields;

public class MockPlatform extends AbstractPlatform {

	@Override
	public String getLabel() {
		return "mock";
	}

	@Override
	public Fields getFields() {
		Field idField = Field.builder("mock").id().create();
		return Fields.create(idField);
	}

}
