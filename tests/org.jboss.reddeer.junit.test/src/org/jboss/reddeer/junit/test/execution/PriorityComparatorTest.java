/******************************************************************************* 
 * Copyright (c) 2016 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.reddeer.junit.test.execution;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jboss.reddeer.junit.execution.IExecutionPriority;
import org.jboss.reddeer.junit.execution.PriorityComparator;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RedDeerSuite.class)
public class PriorityComparatorTest {

	private PriorityComparator priorityComparator = new PriorityComparator();
	
	private IExecutionPriority low1 = new LowPriority();
	private IExecutionPriority low2 = new LowPriority();
	private IExecutionPriority normal1 = new NormalPriority();
	private IExecutionPriority normal2 = new NormalPriority();
	private IExecutionPriority high1 = new HighPriority();
	private IExecutionPriority high2 = new HighPriority();
	
	@Test
	public void testEqualPrioritized() {
		assertTrue("Objects should have been equals", priorityComparator.compare(low1, low2) == 0);
		assertTrue("Objects should have been equals", priorityComparator.compare(normal1, normal2) == 0);
		assertTrue("Objects should have been equals", priorityComparator.compare(high1, high2) == 0);
	}

	@Test
	public void testVariousPriority() {
		assertTrue("Compared prioritized objects order is not correct", 
				priorityComparator.compare(normal1, low1) < 0);
		assertTrue("Compared prioritized objects order is not correct", 
				priorityComparator.compare(high1, normal1) < 0);
		assertTrue("Compared prioritized objects order is not correct", 
				priorityComparator.compare(high1, low1) < 0);
	}
	
	@Test
	public void testOrderOfSortedList() {
		List<IExecutionPriority> unsortedList = new ArrayList<IExecutionPriority>();
		unsortedList.add(normal1);
		unsortedList.add(low1);
		unsortedList.add(high1);
		
		unsortedList.sort(priorityComparator);
		assertTrue("List was not ordered correctly. Order should be from high to low", 
				unsortedList.get(0).equals(high1) && unsortedList.get(1).equals(normal1) &&
				unsortedList.get(2).equals(low1));
	}
}

class LowPriority implements IExecutionPriority {
	@Override
	public long getPriority() {
		return -10;
	}
}

class NormalPriority implements IExecutionPriority {
	@Override
	public long getPriority() {
		return 0;
	}
}

class HighPriority implements IExecutionPriority {
	@Override
	public long getPriority() {
		return 10;
	}
}
