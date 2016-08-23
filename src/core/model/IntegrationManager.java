package core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import core.schema.FieldValueMap;
import facebook.core.FacebookPlatform;

/**
 * A singleton for managing created integrations and available platforms
 */
public class IntegrationManager {

	private static final List<Platform> PLATFORMS = ImmutableList.of(new FacebookPlatform());

	private static final IntegrationManager instance = new IntegrationManager();

	private final List<Integration> integrations = new ArrayList<>();

	private final Map<String, Platform> platformByLabel;

	private IntegrationManager() {
		platformByLabel = PLATFORMS.stream().collect(Collectors.toMap(p -> p.getLabel(), p -> p));
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

	public List<Integration> getAllIntegrations() {
		return integrations;
	}

	public void createOrEditIntegration(Optional<String> label, Platform platform, FieldValueMap map) {
		String integrationId = map.getFields().getIdField().getLabel();
		Optional<Integration> integration = getExistingIntegration(integrationId, platform);
		if (integration.isPresent()) {
			integration.get().setValueMap(map);
		} else {

		}
	}

	public Platform getPlatformByLabel(String platformLabel) {
		return platformByLabel.get(platformLabel);
	}

	private Optional<Integration> getExistingIntegration(String id, Platform platform) {
		return integrations.stream().filter(i -> i.getId().equals(id) && i.getPlatform().equals(platform)).findAny();
	}

}
