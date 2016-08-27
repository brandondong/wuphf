package facebook.core;

import java.util.Optional;

import core.model.AbstractPlatform;
import core.model.Integration;
import core.schema.FieldValueMap;
import core.schema.Fields;
import facebook.schema.FacebookFields;

public class FacebookPlatform extends AbstractPlatform {

	@Override
	public String getLabel() {
		return "facebook";
	}

	@Override
	public Fields getFields() {
		return FacebookFields.getFields();
	}

	@Override
	public Integration createIntegration(Optional<String> label, FieldValueMap fieldValueMap) {
		return new FacebookIntegration(label, fieldValueMap);
	}

}