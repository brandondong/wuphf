package web.model;

import static java.util.stream.Collectors.toList;

import java.util.List;

import core.model.Platform;
import web.schema.FieldWebModel;

public class PlatformWebModel {

	private String label;

	private String logo;

	private String redirectUrl;

	private List<FieldWebModel> userFields;

	private List<FieldWebModel> receiverFields;

	/**
	 * Public constructor for JAXB, use {{@link #createFrom(Platform)} to
	 * instantiate instead
	 */
	public PlatformWebModel() {
	}

	private PlatformWebModel(String label, String logo, String redirectUrl, List<FieldWebModel> userFields,
			List<FieldWebModel> receiverFields) {
		this.label = label;
		this.logo = logo;
		this.redirectUrl = redirectUrl;
		this.userFields = userFields;
		this.receiverFields = receiverFields;
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
				platform.getReceiverFields().stream().map(FieldWebModel::createFrom).collect(toList()));
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public List<FieldWebModel> getUserFields() {
		return userFields;
	}

	public void setUserFields(List<FieldWebModel> userFields) {
		this.userFields = userFields;
	}

	public List<FieldWebModel> getReceiverFields() {
		return receiverFields;
	}

	public void setReceiverFields(List<FieldWebModel> receiverFields) {
		this.receiverFields = receiverFields;
	}

}
