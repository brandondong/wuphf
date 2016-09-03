package core.model;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;

import facebook.core.FacebookPlatform;

/**
 * A singleton for managing available platforms
 */
public class PlatformManager {

	private static final List<Platform> PLATFORMS = ImmutableList.of(new FacebookPlatform());

	private static final PlatformManager instance = new PlatformManager();

	private final Map<String, Platform> platformByLabel;

	private PlatformManager() {
		platformByLabel = PLATFORMS.stream().collect(toMap(p -> p.getLabel(), Function.identity()));
	}

	public static PlatformManager instance() {
		return instance;
	}

	/**
	 * @return a list of all possible platforms that can be created
	 */
	public List<Platform> getAllPlatforms() {
		return PLATFORMS;
	}

	/**
	 * @param platformLabel
	 *            the label of the {@link Platform}
	 * @return the matching {@link Platform} with the given label
	 * @throws IllegalArgumentException
	 *             if the label matches no valid platform
	 */
	public Platform getPlatformByLabel(String platformLabel) {
		checkArgument(platformByLabel.containsKey(platformLabel),
				String.format("No platform found with label %s.", platformLabel));
		return platformByLabel.get(platformLabel);
	}

}
