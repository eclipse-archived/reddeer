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
package org.jboss.reddeer.core.condition;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.hamcrest.Matcher;
import org.jboss.reddeer.common.condition.AbstractWaitCondition;
import org.jboss.reddeer.common.matcher.AndMatcher;
import org.jboss.reddeer.common.platform.RunningPlatform;
import org.jboss.reddeer.core.handler.WidgetHandler;
import org.jboss.reddeer.core.lookup.WidgetLookup;

/**
 * WidgetIsFound is general condition to find desired widget
 * @author Jiri Peterka
 * 
 * @param <T> widget class
 */
public class WidgetIsFound <T extends Widget> extends AbstractWaitCondition {

	private Control parent;
	private AndMatcher am;
	private int index;
	private Widget properWidget;
	WidgetLookup widgetLookup = WidgetLookup.getInstance();
	
	/**
	 * Looks for widgets under given parent control with given index and matching matchers.
	 *
	 * @param <T> the generic type
	 * @param parent given parent control
	 * @param index given index
	 * @param matchers given matchers
	 */
	@SuppressWarnings("hiding")
	public <T extends Widget> WidgetIsFound(Control parent, int index, Matcher<?>... matchers) {
		if (parent == null) {
			this.parent = widgetLookup.findParent();
		} else {
			this.parent = parent;
		}
		this.am=new AndMatcher(matchers);
		this.index=index;
	}

	/**
	 * Looks for first widget under given parent control matching matchers.
	 *
	 * @param <T> the generic type
	 * @param parent given parent control
	 * @param matchers given matchers
	 */	
	@SuppressWarnings("hiding")
	public <T extends Widget> WidgetIsFound(Control parent, Matcher<?>... matchers) {
		this(parent, 0, matchers);
	}

	/**
	 * Looks for first widget under default parent control matching matchers.
	 *
	 * @param <T> the generic type
	 * @param matchers given matchers
	 */		
	@SuppressWarnings("hiding")
	public <T extends Widget> WidgetIsFound(Matcher<?>... matchers) {		
		this(null,0, matchers);
	}

	/**
	 * Tests if given widget is found.
	 *
	 * @return true if widget is found, false otherwise
	 */
	public boolean test() {
		
		properWidget = widgetLookup.activeWidget(parent, am, index);

		if(properWidget == null){
			return false;
		}
		return true;
	}

	/**
	 * Returns found widget.
	 *
	 * @return found widget
	 */
	public Widget getWidget(){
		if (properWidget != null) {
			setFocus();
		}
		return properWidget;
	}
	
	/**
	 * Returns condition description.
	 *
	 * @return the string
	 */
	@Override
	public String description() {
		return "widget is found";
	}

	private void setFocus(){
		if (RunningPlatform.isWindows() && properWidget instanceof Button &&
				((WidgetHandler.getInstance().getStyle((Button)properWidget) & SWT.RADIO) != 0)){
			// do not set focus because it also select radio button on Windows
		} else {
			WidgetHandler.getInstance().setFocus(properWidget);
		}
	}
}
