package org.jboss.reddeer.junit.internal.requirement.inject;

import java.lang.annotation.Annotation;

import org.jboss.reddeer.junit.requirement.PropertyConfiguration;
import org.jboss.reddeer.junit.requirement.Requirement;

public class RequirementA implements Requirement<Annotation>, PropertyConfiguration {

	private String a;
	
	public boolean canFulfill() {
		return false;
	}

	public void fulfill() {
	}
	
	public void setA(String a) {
		this.a = a;
	}
	
	public String getA() {
		return a;
	}
	
	@Override
	public void setDeclaration(Annotation declaration) {
	}
}
