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
package org.jboss.reddeer.eclipse.ui.ide;

import org.jboss.reddeer.common.wait.WaitUntil;
import org.jboss.reddeer.common.wait.WaitWhile;
import org.jboss.reddeer.core.condition.ShellWithTextIsAvailable;
import org.jboss.reddeer.swt.condition.WidgetIsEnabled;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;

/**
 * Represents Quick Fix dialog
 * 
 * @author ldimaggi
 *
 */
public class RepoConnectionDialog extends DefaultShell {

	public static final String TITLE = "Properties for Task Repository";
	
	/**
	 * Open QuickFix dialog and set focus on it.
	 */
	public RepoConnectionDialog() {
		super(TITLE);
	}
	
	/**
	 * Press Select All button.
	 */
	public void selectAll() {
		new PushButton("Select All").click();
	}
	
	/**
	 * Press Deselect All button.
	 */
	public void deselectAll() {
		new PushButton("Deselect All").click();
	}
	
	/**
	 * Press Cancel button.
	 */
	public void cancel() {
		new PushButton("Cancel").click();
		new WaitWhile(new ShellWithTextIsAvailable(TITLE));
	}
	
	/**
	 * Press Finish button.
	 */
	public void finish() {
		new PushButton("Finish").click();
		new WaitWhile(new ShellWithTextIsAvailable(TITLE));
	}
	
	/**
	 * Press Validate button - Check for the Validate button before and after it is clicked
	 * as validation can be slow.
	 */
	public void validateSettings() {
		new WaitUntil(new WidgetIsEnabled(new PushButton("Validate Settings")));
		new PushButton("Validate Settings").click();
		new WaitUntil(new WidgetIsEnabled(new PushButton("Validate Settings")));
	}	
	
}
