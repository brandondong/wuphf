package core.model;

import java.util.Optional;

import core.schema.FieldValueMap;

public abstract class AbstractIntegration implements Integration {

	private final String id;

	private final Optional<String> label;

	private final Platform platform;

	private final FieldValueMap fieldValueMap;

	public AbstractIntegration(Optional<String> label, Platform platform, FieldValueMap fieldValueMap) {
		id = fieldValueMap.getIdValue();
		this.label = label;
		this.platform = platform;
		this.fieldValueMap = fieldValueMap;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Optional<String> getLabel() {
		return label;
	}

	@Override
	public Platform getPlatform() {
		return platform;
	}

	@Override
	public FieldValueMap getFieldValueMap() {
		return fieldValueMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((platform == null) ? 0 : platform.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractIntegration other = (AbstractIntegration) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (platform == null) {
			if (other.platform != null)
				return false;
		} else if (!platform.equals(other.platform))
			return false;
		return true;
	}

}
