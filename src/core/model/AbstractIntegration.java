package core.model;

public abstract class AbstractIntegration implements Integration {

	private final String id;

	private final Platform platform;

	public AbstractIntegration(String id, Platform platform) {
		this.id = id;
		this.platform = platform;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Platform getPlatform() {
		return platform;
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
