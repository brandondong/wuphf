package util.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class APIKeyReader {

	public String getKeyFromFile(String filename) {
		Path keyPath = Paths.get(String.format("keys/%s", filename));
		try {
			return Files.readAllLines(keyPath).get(0);
		} catch (IOException e) {
			throw new IllegalStateException("Expected to be able to read API key", e);
		}
	}

}
