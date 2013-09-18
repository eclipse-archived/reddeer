package org.jboss.reddeer.junit.internal.requirement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jboss.reddeer.junit.requirement.Requirement;
import org.junit.Test;

public class RequirementsTest {

	private Requirements requirements;
	
	@Test(expected=IllegalArgumentException.class)
	public void constructor_nullList(){
		new Requirements(null);
	}
	
	@Test
	public void size() {
		Requirement<?> requirement1 = mock(Requirement.class);
		Requirement<?> requirement2 = mock(Requirement.class);
		
		requirements = new Requirements(asList(requirement1, requirement2));

		assertThat(requirements.size(), is(2));
	}
	
	@Test
	public void iterator() {
		Requirement<?> requirement1 = mock(Requirement.class);
		Requirement<?> requirement2 = mock(Requirement.class);
		
		requirements = new Requirements(asList(requirement1, requirement2));
		Iterator<Requirement<?>> iterator = requirements.iterator();
		
		assertSame(requirement1, iterator.next());
		assertSame(requirement2, iterator.next());
	}
	
	@Test
	public void canFulfill_emptyList() {
		requirements = new Requirements(new ArrayList<Requirement<?>>());
		
		assertTrue(requirements.canFulfill());
	}
	
	@Test
	public void canFulfill_yes() {
		Requirement<?> requirement1 = mock(Requirement.class);
		when(requirement1.canFulfill()).thenReturn(true);
		
		Requirement<?> requirement2 = mock(Requirement.class);
		when(requirement2.canFulfill()).thenReturn(true);
		
		requirements = new Requirements(asList(requirement1, requirement2));
		
		assertTrue(requirements.canFulfill());
	}
	
	@Test
	public void canFulfill_no() {
		Requirement<?> requirement1 = mock(Requirement.class);
		when(requirement1.canFulfill()).thenReturn(true);
		
		Requirement<?> requirement2 = mock(Requirement.class);
		when(requirement2.canFulfill()).thenReturn(false);
		
		requirements = new Requirements(asList(requirement1, requirement2));
		
		assertFalse(requirements.canFulfill());
	}
	
	@Test
	public void fulfill() {
		Requirement<?> requirement1 = mock(Requirement.class);
		Requirement<?> requirement2 = mock(Requirement.class);

		requirements = new Requirements(asList(requirement1, requirement2));
		requirements.fulfill();
		
		verify(requirement1).fulfill();
		verify(requirement2).fulfill();
	}
	
	private List<Requirement<?>> asList(Requirement<?>... requirements) {
		return Arrays.asList(requirements);
	}
}
