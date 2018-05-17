/*******************************************************************************
 * Copyright (c) 2017 Red Hat, Inc and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Red Hat, Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.reddeer.junit.test.internal.requirement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.reddeer.junit.internal.requirement.Requirements;
import org.eclipse.reddeer.junit.requirement.Requirement;
import org.junit.Test;

public class RequirementsTest {

	private Requirements requirements;
	
	@Test(expected=IllegalArgumentException.class)
	public void constructor_nullList(){
		new Requirements(null, null, null);
	}
	
	@Test
	public void size() {
		Requirement<?> requirement1 = mock(Requirement.class);
		Requirement<?> requirement2 = mock(Requirement.class);
		
		requirements = new Requirements(asList(requirement1, requirement2), String.class, null);

		assertThat(requirements.size(), is(2));
	}
	
	@Test
	public void iterator() {
		Requirement<?> requirement1 = mock(Requirement.class);
		Requirement<?> requirement2 = mock(Requirement.class);
		
		requirements = new Requirements(asList(requirement1, requirement2), String.class, null);
		Iterator<Requirement<?>> iterator = requirements.iterator();
		
		assertSame(requirement1, iterator.next());
		assertSame(requirement2, iterator.next());
	}
	
	@Test
	public void fulfill() {
		Requirement<?> requirement1 = mock(Requirement.class);
		Requirement<?> requirement2 = mock(Requirement.class);

		requirements = new Requirements(asList(requirement1, requirement2), String.class, null);
		requirements.fulfill();
		
		verify(requirement1).fulfill();
		verify(requirement2).fulfill();
	}
	
	@Test
	public void cleanup() {
		Requirement<?> requirement1 = mock(Requirement.class);
		Requirement<?> requirement2 = mock(Requirement.class);

		requirements = new Requirements(asList(requirement1, requirement2), String.class, null);
		requirements.cleanUp();
		
		verify(requirement1).cleanUp();
		verify(requirement2).cleanUp();
	}
	
	@Test
	public void sortByPriority() {
		Requirement<?> requirement1 = new TestRequirementA();
		Requirement<?> requirement2 = new TestRequirementB();
		Requirement<?> requirement3 = new TestRequirementC();
		Requirements requirements = new Requirements(
					asList(requirement1, requirement2, requirement3),String.class, null);
		Iterator<Requirement<?>> iterator = requirements.iterator();
		assertSame("TestRequirementC was expected", requirement3, iterator.next());
		assertSame("TestRequirementB was expected", requirement2, iterator.next());
		assertSame("TestRequirementA was expected", requirement1, iterator.next());
	}
	
	private List<Requirement<?>> asList(Requirement<?>... requirements) {
		return Arrays.asList(requirements);
	}
}
