package facebook.core;

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
	public Integration createIntegration(FieldValueMap fieldValueMap) {
		return new FacebookIntegration(fieldValueMap);
	}

	@Override
	public String getLogoImageLink() {
		return "facebook-logo.png";
	}

}
