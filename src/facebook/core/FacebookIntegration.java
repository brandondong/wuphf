package facebook.core;

import java.util.Optional;

import core.model.AbstractIntegration;
import core.schema.FieldValueMap;

public class FacebookIntegration extends AbstractIntegration {

	public FacebookIntegration(Optional<String> label, FieldValueMap fieldValueMap) {
		super(label, new FacebookPlatform(), fieldValueMap);
	}

	@Override
	public void post(String message) {
		// TODO Auto-generated method stub
	}

}
