package facebook.core;

import core.model.Integration;
import core.model.Platform;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class FacebookPlatform implements Platform {

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
