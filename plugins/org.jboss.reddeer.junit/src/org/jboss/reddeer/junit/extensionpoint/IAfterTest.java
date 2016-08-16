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
package org.jboss.reddeer.junit.extensionpoint;

import org.jboss.reddeer.junit.execution.IExecutionPriority;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

/**
 * Eclipse extension for running some action after @AfterClass methods or after @After methods
 *
 */
public interface IAfterTest extends IExecutionPriority {

	/**
	 * Contains action proceeded after @AfterClass methods. Method is run only when
	 * method hasToRun() returns true
	 * 
	 * @param config Config ID
	 * @param testClass Test class
	 */
	public void runAfterTestClass(String config, TestClass testClass);
	
	/**
	 * Contains action proceeded after each method. Method is run only when
	 * method hasToRun() returns true
	 * 
	 * @param config Config ID
	 * @param target Target object
	 * @param method Test method
	 */
	public void runAfterTest(String config, Object target, FrameworkMethod method);

	/**
	 * Returns true when method runAfterTest() has to run.
	 *
	 * @return true, if successful
	 */
	public boolean hasToRun();
}
