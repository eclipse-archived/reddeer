package org.jboss.reddeer.junit.internal.requirement.inject;

import org.jboss.reddeer.junit.requirement.inject.InjectRequirement;

public class NoRequirementConfigTestMock {

	@InjectRequirement
	private RequirementB requirementA;
	
	public RequirementB getRequirementA() {
		return requirementA;
	}
	
}
