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
package org.eclipse.reddeer.junit.internal.runner.statement;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.reddeer.common.logging.Logger;
import org.eclipse.reddeer.junit.extensionpoint.IAfterTest;
import org.eclipse.reddeer.junit.screenshot.ScreenshotCapturer;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

/**
 * 
 * Statement which runs methods of defined extensions. Upon failure a screenshot is captured.
 * 
 * @author Lucia Jelinkova
 *
 */
public class RunIAfterTestExtensions extends AbstractStatementWithScreenshot {

	private static final Logger log = Logger.getLogger(RunIAfterTestExtensions.class);

	private final List<IAfterTest> afters;

	/**
	 * Instantiates a new run i after test extensions.
	 *
	 * @param config the config
	 * @param next the next
	 * @param testClass the test class
	 * @param method the method
	 * @param target the target
	 * @param afters the afters
	 */
	public RunIAfterTestExtensions(String config, Statement next, TestClass testClass, FrameworkMethod method, 
			Object target, List<IAfterTest> afters) {
		super(config, next, testClass, method, target);
		this.afters = afters;
	}

	@Override
	public void evaluate() throws Throwable {
		List<Throwable> errors = new ArrayList<Throwable>();

		try {
			nextStatement.evaluate();
		} catch (Throwable e) {
			errors.add(e);
		} 

		IAfterTest after = null;

		log.debug("Run after test extensions for test class " + testClass.getJavaClass().getName());
		for (IAfterTest each : afters) {
			try {
				after = each;
				if (after.hasToRun()){
					log.debug("Run method runAfterTest() of class " + after.getClass().getCanonicalName());
					after.runAfterTest(config, target, frameworkMethod);
				}
			} catch (Throwable e) {
				if(ScreenshotCapturer.shouldCaptureScreenshotOnException(e)){
					log.error("Run method runAfterTest() of class " + after.getClass().getCanonicalName() + " failed", e);
					createScreenshot("AfterTestExt", after.getClass());
				}
				errors.add(e);
			}
		}
		MultipleFailureException.assertEmpty(errors);
	}
}
