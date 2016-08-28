package web.model;

import core.model.Platform;

public class PlatformWebModel {

	private String label;

	private String logoImageLink;

	/**
	 * Public constructor for JAXB, use {{@link #createFrom(Platform)} to
	 * instantiate instead
	 */
	public PlatformWebModel() {
	}

	private PlatformWebModel(String label, String logoImageLink) {
		this.label = label;
		this.setLogoImageLink(logoImageLink);
	}

	/**
	 * 
	 * @param platform
	 *            the {@link Platform} to create from
	 * @return a {@link PlatformWebModel} based on the provided {@link Platform}
	 */
	public static PlatformWebModel createFrom(Platform platform) {
		return new PlatformWebModel(platform.getLabel(), platform.getLogoImageLink());
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLogoImageLink() {
		return logoImageLink;
	}

	public void setLogoImageLink(String logoImageLink) {
		this.logoImageLink = logoImageLink;
	}

}
