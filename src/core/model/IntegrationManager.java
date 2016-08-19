package core.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

import facebook.core.FacebookPlatform;

/**
 * A singleton for managing created integrations and available platforms
 */
public class IntegrationManager {

	private static final List<Platform> PLATFORMS = ImmutableList.of(new FacebookPlatform());

	private static final IntegrationManager instance = new IntegrationManager();

	private IntegrationManager() {
	}

	public static IntegrationManager instance() {
		return instance;
	}

	/**
	 * 
	 * @return a list of all possible platforms that can be created
	 */
	public List<Platform> getAllPlatforms() {
		return PLATFORMS;
	}

}
