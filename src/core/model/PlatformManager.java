package core.model;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;

import facebook.core.FacebookPlatform;
import reddit.core.RedditPlatform;
import slack.core.SlackPlatform;

/**
 * A singleton for managing available platforms
 */
public class PlatformManager implements IPlatformManager {

	private static final List<Platform> PLATFORMS = ImmutableList.of(new FacebookPlatform(), new RedditPlatform(),
			new SlackPlatform());

	private static final IPlatformManager instance = new PlatformManager();

	private final Map<String, Platform> platformByLabel;

	private PlatformManager() {
		platformByLabel = PLATFORMS.stream().collect(toMap(p -> p.getLabel(), Function.identity()));
	}

	public static IPlatformManager instance() {
		return instance;
	}

	@Override
	public List<Platform> getAllPlatforms() {
		return PLATFORMS;
	}

	@Override
	public Platform getPlatformByLabel(String platformLabel) {
		checkArgument(platformByLabel.containsKey(platformLabel),
				String.format("No platform found with label %s.", platformLabel));
		return platformByLabel.get(platformLabel);
	}

}
