package web.model;

import core.model.Platform;

public class PlatformResource {

	private final String label;

	private PlatformResource(String label) {
		this.label = label;
	}

	/**
	 * 
	 * @param platform
	 *            the {@link Platform} to create from
	 * @return a {@link PlatformResource} based on the provided {@link Platform}
	 */
	public static PlatformResource createFrom(Platform platform) {
		return new PlatformResource(platform.getLabel());
	}

	public String getLabel() {
		return label;
	}

}
