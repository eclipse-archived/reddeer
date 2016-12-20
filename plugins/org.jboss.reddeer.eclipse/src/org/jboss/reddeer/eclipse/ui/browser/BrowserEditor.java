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
package org.jboss.reddeer.eclipse.ui.browser;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.browser.WebBrowserEditor;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jboss.reddeer.common.matcher.MatcherBuilder;
import org.jboss.reddeer.common.wait.TimePeriod;
import org.jboss.reddeer.common.wait.WaitUntil;
import org.jboss.reddeer.common.wait.WaitWhile;
import org.jboss.reddeer.core.matcher.WithTextMatcher;
import org.jboss.reddeer.eclipse.condition.BrowserHasURL;
import org.jboss.reddeer.swt.condition.PageIsLoaded;
import org.jboss.reddeer.swt.impl.browser.InternalBrowser;
import org.jboss.reddeer.workbench.impl.editor.AbstractEditor;
import org.jboss.reddeer.workbench.matcher.EditorPartTitleMatcher;

/**
 * Represents a browser editor.
 */
public class BrowserEditor extends AbstractEditor{
	
	private InternalBrowser browser;
	private static final TimePeriod TIMEOUT = TimePeriod.LONG;
	
	/**
	 * Constructs the browser editor with a given title.
	 * 
	 * @param title Title
	 */
	public BrowserEditor(String title){
		this(new WithTextMatcher(title));
	}
	
	/**
	 * Constructs the browser editor with a given title matcher.
	 * 
	 * @param titleMatcher Title matcher
	 */
	@SuppressWarnings("unchecked")
	public BrowserEditor(Matcher<String> titleMatcher){
		super(createBrowserEditorMatchers(titleMatcher));
		browser = new InternalBrowser();
	}
	
	/**
	 * Opens page with given URL in browser.
	 *
	 * @param url the url
	 */
	public void openPageURL(String url) {
		browser.setURL(url);
		new WaitUntil(new PageIsLoaded(browser), TIMEOUT);
	}

	/**
	 * Refreshes currently opened page in browser.
	 */
	public void refreshPage() {
		browser.setURL(browser.getURL());
		new WaitUntil(new PageIsLoaded(browser), TIMEOUT);
	}

	/**
	 * Go to the previous page in browser.
	 */
	public void back() {
		String prevUrl = browser.getURL();
		browser.back();
		new WaitWhile(new BrowserHasURL(this, prevUrl), TIMEOUT);
		new WaitUntil(new PageIsLoaded(browser), TIMEOUT);
	}

	/**
	 * Go to the next page in browser.
	 */
	public void forward() {
		String prevUrl = browser.getURL();
		browser.forward();
		new WaitWhile(new BrowserHasURL(this, prevUrl), TIMEOUT);
		new WaitUntil(new PageIsLoaded(browser), TIMEOUT);
	}

	/**
	 * Gets URL of the currently opened page.
	 *
	 * @return String URL of the current page
	 */
	public String getPageURL() {
		return browser.getURL();
	}
	
	/**
	 * Gets Text of the currently opened page.
	 *
	 * @return String Text of the current page
	 */
	public String getText() {
		return browser.getText();
	}

	@SuppressWarnings("rawtypes")
	private static Matcher[] createBrowserEditorMatchers(Matcher<String> titleMatcher){ 
		Matcher[] matchers = MatcherBuilder.getInstance().addMatcher(new Matcher[0], new BrowserEditorMatcher());
		matchers = MatcherBuilder.getInstance().addMatcher(matchers, new EditorPartTitleMatcher(titleMatcher));
		return matchers;
	}
	
	private static class BrowserEditorMatcher extends TypeSafeMatcher<IEditorPart>{

		@Override
		public void describeTo(Description description) {
			description.appendText("Editor is of type WebBrowserEditor");
		}

		@Override
		protected boolean matchesSafely(IEditorPart item) {
			return (item instanceof WebBrowserEditor); 
		}
		
	}
	
}
