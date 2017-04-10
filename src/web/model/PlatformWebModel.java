package web.model;

import static java.util.stream.Collectors.toList;

import java.util.List;

import core.model.Platform;
import web.schema.FieldWebModel;

public class PlatformWebModel {

	private final String label;

	private final String logo;

	private final String redirectUrl;

	private final List<FieldWebModel> userFields;

	private final List<FieldWebModel> receiverFields;

	private final String description;

	private PlatformWebModel(String label, String logo, String redirectUrl, List<FieldWebModel> userFields,
			List<FieldWebModel> receiverFields, String description) {
		this.label = label;
		this.logo = logo;
		this.redirectUrl = redirectUrl;
		this.userFields = userFields;
		this.receiverFields = receiverFields;
		this.description = description;
	}

	/**
	 * 
	 * @param platform
	 *            the {@link Platform} to create from
	 * @return a {@link PlatformWebModel} based on the provided {@link Platform}
	 */
	public static PlatformWebModel createFrom(Platform platform) {
		return new PlatformWebModel(platform.getLabel(), platform.getLogoImageLink(), platform.getLoginRedirectUrl(),
				platform.getUserFields().stream().map(FieldWebModel::createFrom).collect(toList()),
				platform.getReceiverFields().stream().map(FieldWebModel::createFrom).collect(toList()),
				platform.getDescription().orElse(null));
	}

	public String getLabel() {
		return label;
	}

	public String getLogo() {
		return logo;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public List<FieldWebModel> getUserFields() {
		return userFields;
	}

	public List<FieldWebModel> getReceiverFields() {
		return receiverFields;
	}

	public String getDescription() {
		return description;
	}

}
