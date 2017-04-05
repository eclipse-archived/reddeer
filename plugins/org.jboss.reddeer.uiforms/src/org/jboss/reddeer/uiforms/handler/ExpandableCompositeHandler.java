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
package org.jboss.reddeer.uiforms.handler;

import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.jboss.reddeer.common.util.Display;
import org.jboss.reddeer.common.util.ResultRunnable;
import org.jboss.reddeer.core.handler.ControlHandler;

/**
 * Contains methods for handling UI operations on {@link ExpandableComposite} widgets.
 *
 * @author Radoslav Rabara
 *
 */
public class ExpandableCompositeHandler extends ControlHandler{
	
	private static ExpandableCompositeHandler instance;
	
	/**
	 * Gets instance of ExpandableCompositeHandler.
	 * 
	 * @return instance of ExpandableCompositeHandler
	 */
	public static ExpandableCompositeHandler getInstance(){
		if(instance == null){
			instance = new ExpandableCompositeHandler();
		}
		return instance;
	}

	/**
	 * Sets the expansion state to the specified {@link ExpandableComposite}.
	 *
	 * @param composite {@link ExpandableComposite} to handle
	 * @param expanded the new expanded state
	 */
	public void setExpanded(final ExpandableComposite composite, final boolean expanded) {
		Display.syncExec(new Runnable() {
			@Override
			public void run() {
				composite.setExpanded(expanded);
			}
		});
	}

	/**
	 * Returns the expansion state of the specified {@link ExpandableComposite}.
	 *
	 * @param composite {@link ExpandableComposite} to handle
	 *
	 * @return <code>true</code> if expanded, <code>false</code> if collapsed.
	 */
	public boolean isExpanded(final ExpandableComposite composite) {
		boolean expansionState = Display
				.syncExec(new ResultRunnable<Boolean>() {
					@Override
					public Boolean run() {
						return composite.isExpanded();
					}
				});
		return expansionState;
	}
	
	/**
	 * Gets text of expandable composite
	 * @param composite to handle
	 * @return text of specified expandable composite
	 */
	public String getText(final ExpandableComposite composite){
		return Display.syncExec(new ResultRunnable<String>() {

			@Override
			public String run() {
				return composite.getText();
			}
		});
	}
}
