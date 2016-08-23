package facebook.core;

import core.model.AbstractPlatform;
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

}
