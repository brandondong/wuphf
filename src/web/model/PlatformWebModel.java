package web.model;

import static java.util.stream.Collectors.toList;

import java.util.List;

import core.model.Platform;
import web.schema.FieldWebModel;

public class PlatformWebModel {

	private String label;

	private String logoImageLink;

	private List<FieldWebModel> fields;

	/**
	 * Public constructor for JAXB, use {{@link #createFrom(Platform)} to
	 * instantiate instead
	 */
	public PlatformWebModel() {
	}

	private PlatformWebModel(String label, String logoImageLink, List<FieldWebModel> fields) {
		this.label = label;
		this.setLogoImageLink(logoImageLink);
		this.fields = fields;
	}

	/**
	 * 
	 * @param platform
	 *            the {@link Platform} to create from
	 * @return a {@link PlatformWebModel} based on the provided {@link Platform}
	 */
	public static PlatformWebModel createFrom(Platform platform) {
		return new PlatformWebModel(platform.getLabel(), platform.getLogoImageLink(),
				platform.getFields().stream().map(FieldWebModel::createFrom).collect(toList()));
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

	public List<FieldWebModel> getFields() {
		return fields;
	}

	public void setFields(List<FieldWebModel> fields) {
		this.fields = fields;
	}

}
