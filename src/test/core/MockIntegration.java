package test.core;

import core.model.AbstractIntegration;

public class MockIntegration extends AbstractIntegration {

	public MockIntegration(String id) {
		super(id, new MockPlatform());
	}

	@Override
	public void post(String message) {
	}

}
