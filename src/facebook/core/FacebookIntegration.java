package facebook.core;

import core.model.Integration;
import core.schema.FieldValueMap;
import facebook.schema.FacebookFields;

public class FacebookIntegration implements Integration {

	private final String username;

	private final String password;

	FacebookIntegration(FieldValueMap fieldValueMap) {
		username = fieldValueMap.getValueForField(FacebookFields.EMAIL);
		password = fieldValueMap.getValueForField(FacebookFields.PASSWORD);
	}

	@Override
	public void post(String message) {
		// TODO Auto-generated method stub
		System.out.println("Username: " + username + " Password: " + password);
	}

}
