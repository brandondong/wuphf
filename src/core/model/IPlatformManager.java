package core.model;

import java.util.List;

public interface IPlatformManager {

	/**
	 * @return a list of all possible platforms that can be created
	 */
	List<Platform> getAllPlatforms();

	/**
	 * @param platformLabel
	 *            the label of the {@link Platform}
	 * @return the matching {@link Platform} with the given label
	 * @throws IllegalArgumentException
	 *             if the label matches no valid platform
	 */
	Platform getPlatformByLabel(String platformLabel);

}