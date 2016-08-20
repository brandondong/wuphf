package core.model;

public abstract class AbstractPlatform implements Platform {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
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
		AbstractPlatform other = (AbstractPlatform) obj;
		if (getLabel() == null) {
			if (other.getLabel() != null)
				return false;
		} else if (!getLabel().equals(other.getLabel()))
			return false;
		return true;
	}

}
