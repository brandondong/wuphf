package reddit.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import core.model.Integration;
import core.model.Platform;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class RedditPlatform implements Platform {

	private static final String API_KEY = getKeyFromFile();

	@Override
	public String getLabel() {
		return "reddit";
	}

	@Override
	public Fields getUserFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fields getReceiverFields() {
		return RedditFields.getReceiverFields();
	}

	@Override
	public Integration createIntegration(FieldValueMap fieldValueMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogoImageLink() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLoginRedirectUrl() {
		// TODO incorporate this into the reddit auth url
		return API_KEY;
	}

	private static String getKeyFromFile() {
		Path keyPath = Paths.get("keys/reddit");
		try {
			return Files.readAllLines(keyPath).get(0);
		} catch (IOException e) {
			throw new IllegalStateException("Expected to be able to read API key", e);
		}
	}

}
