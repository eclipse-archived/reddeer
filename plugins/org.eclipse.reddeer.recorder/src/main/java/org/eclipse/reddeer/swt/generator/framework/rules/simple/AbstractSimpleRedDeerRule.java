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
package org.eclipse.reddeer.swt.generator.framework.rules.simple;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.generator.framework.GenerationSimpleRule;

/**
 * This class represents abstract reddeer rule for swt generator
 * @author rawagner
 *
 */
public abstract class AbstractSimpleRedDeerRule extends GenerationSimpleRule{
	
	private String shellTitle;
	private String viewTitle;
	protected Widget widget;
	
	/**
	 * Sets title of shell which contains widget
	 * @param shellTitle title of shell
	 */
	public void setShellTitle(String shellTitle){
		this.shellTitle = shellTitle;
	}
	
	/**
	 * Sets title of view which contains widget
	 * @param viewTitle title of view
	 */
	public void setViewTitle(String viewTitle){
		this.viewTitle = viewTitle;
	}
	
	/**
	 * Returns title of shell which contains widget
	 * @return shell title
	 */
	public String getShellTitle(){
		return shellTitle;
	}
	
	/**
	 * Returns title of view which contains widget
	 * @return view title
	 */
	public String getViewTitle(){
		return viewTitle;
	}

	@Override
	public Widget getWidget() {
		return widget;
	}
	
}