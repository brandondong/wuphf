package web.model;

import core.model.Platform;

public class PlatformWebModel {

	private final String label;

	private PlatformWebModel(String label) {
		this.label = label;
	}

	/**
	 * 
	 * @param platform
	 *            the {@link Platform} to create from
	 * @return a {@link PlatformWebModel} based on the provided {@link Platform}
	 */
	public static PlatformWebModel createFrom(Platform platform) {
		return new PlatformWebModel(platform.getLabel());
	}

	public String getLabel() {
		return label;
	}

}
