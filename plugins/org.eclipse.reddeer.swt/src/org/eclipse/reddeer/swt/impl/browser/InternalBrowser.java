/*******************************************************************************
 * Copyright (c) 2017 Red Hat, Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.reddeer.swt.impl.browser;

import org.hamcrest.Matcher;
import org.eclipse.reddeer.core.matcher.WithLabelMatcher;
import org.eclipse.reddeer.core.reference.ReferencedComposite;
/**
 * Implements SWT Browser manipulations
 * @author Jiri Peterka, Vlado Pakan
 *
 */
public class InternalBrowser extends AbstractBrowser{
	
	/**
	 * Finds first Browser.
	 */
	public InternalBrowser() {
		this((ReferencedComposite) null);
	}
	
	public InternalBrowser(org.eclipse.swt.browser.Browser widget){
		super(widget);
	}
	
	/**
	 * Finds first Browser inside given composite.
	 *
	 * @param referencedComposite the referenced composite
	 */
	public InternalBrowser(ReferencedComposite referencedComposite) {
		this(referencedComposite, 0);
	}
	
	/**
	 * Finds Browser specified by label.
	 *
	 * @param label the label
	 */
	public InternalBrowser(String label) {
		this(null, label);
	}
	
	/**
	 * Browser that matches given matchers.
	 *
	 * @param matchers the matchers
	 */
	public InternalBrowser(Matcher<?>... matchers) {
		this(null, matchers);
	}
	
	/**
	 * Finds Browser specified by label inside given composite.
	 *
	 * @param referencedComposite the referenced composite
	 * @param label the label
	 */
	public InternalBrowser(ReferencedComposite referencedComposite, String label) {
		this(referencedComposite, 0, new WithLabelMatcher(label));
	}
	
	/**
	 * Browser that matches given matchers.
	 *
	 * @param referencedComposite the referenced composite
	 * @param matchers the matchers
	 */
	public InternalBrowser(ReferencedComposite referencedComposite, Matcher<?>... matchers) {
		this(referencedComposite, 0, matchers);
	}
	
	/**
	 * Finds Browser specified by index that matches given matchers.
	 *
	 * @param index the index
	 * @param matchers the matchers
	 */
	public InternalBrowser(int index, Matcher<?>... matchers) {
		this(null, index, matchers);
	}
	
	/**
	 * Finds Browser specified by index inside given composite that matches given matchers.
	 *
	 * @param referencedComposite the referenced composite
	 * @param index the index
	 * @param matchers the matchers
	 */
	public InternalBrowser(ReferencedComposite referencedComposite, int index, Matcher<?>... matchers) {
		super(referencedComposite, index, matchers);
	}
}
