package core.model;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		platformByLabel = PLATFORMS.stream().collect(toMap(p -> p.getLabel(), p -> p));
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
		Optional<Integration> integration = getExistingIntegration(map.getIdValue(), platform);
		if (integration.isPresent()) {
			integration.get().setValueMap(map);
		} else {
			integrations.add(platform.createIntegration(label, map));
		}
	}

	public Platform getPlatformByLabel(String platformLabel) {
		checkArgument(platformByLabel.containsKey(platformLabel),
				String.format("No platform found with label %s.", platformLabel));
		return platformByLabel.get(platformLabel);
	}

	private Optional<Integration> getExistingIntegration(String id, Platform platform) {
		return integrations.stream().filter(i -> i.getId().equals(id) && i.getPlatform().equals(platform)).findAny();
	}

}
