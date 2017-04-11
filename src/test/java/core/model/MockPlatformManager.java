package core.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import com.google.common.collect.ImmutableList;

import core.model.IPlatformManager;
import core.model.Platform;

public class MockPlatformManager implements IPlatformManager {

	@Override
	public List<Platform> getAllPlatforms() {
		return ImmutableList.of(new MockPlatform());
	}

	@Override
	public Platform getPlatformByLabel(String platformLabel) {
		checkArgument(platformLabel.equals(MockPlatform.LABEL), "Wrong label for mock platform.");
		return new MockPlatform();
	}

}
