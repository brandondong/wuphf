package test.core;

import java.util.Optional;

import core.model.AbstractIntegration;

public class MockIntegration extends AbstractIntegration {

	public MockIntegration(String id) {
		super(id, Optional.empty(), new MockPlatform());
	}

	@Override
	public void post(String message) {
	}

}
