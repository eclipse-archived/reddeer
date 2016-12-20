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
package org.jboss.reddeer.eclipse.rse.ui.view;

import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.common.wait.TimePeriod;
import org.jboss.reddeer.common.wait.WaitUntil;
import org.jboss.reddeer.common.wait.WaitWhile;
import org.jboss.reddeer.core.condition.ShellWithTextIsAvailable;
import org.jboss.reddeer.core.exception.CoreLayerException;
import org.jboss.reddeer.eclipse.condition.RemoteSystemExists;
import org.jboss.reddeer.eclipse.condition.RemoteSystemIsConnected;
import org.jboss.reddeer.eclipse.rse.ui.wizard.SystemPasswordPromptDialog;
import org.jboss.reddeer.swt.api.TreeItem;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.menu.ContextMenu;
import org.jboss.reddeer.swt.impl.text.LabeledText;
import org.jboss.reddeer.workbench.core.condition.JobIsRunning;

/**
 * This class represents a system on {@link SystemView}. It contains the system label
 * and operations that can be invoked on the remote system (Connect, Disconnect, Delete). 
 * @author Pavol Srna
 *
 */
public class System {
	
	private static final TimePeriod TIMEOUT = TimePeriod.VERY_LONG;
	
	private static final Logger log = Logger.getLogger(System.class);
	
	protected TreeItem treeItem;
	protected String label;
	
	/**
	 * Instantiates a new system.
	 *
	 * @param treeItem the tree item
	 */
	public System(TreeItem treeItem) {
		this.treeItem = treeItem;
		this.label = treeItem.getText();
	}
	
	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel(){
		return label;
	}
	
	/**
	 * Connect to Remote System without password.
	 *
	 * @param username the username
	 */
	public void connect(String username){
		connect(username, "");
	}
	
	/**
	 * Connect to Remote System.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public void connect(String username, String password){
		log.info("Connecting to remote system " + getLabel());
		select();
		new ContextMenu("Connect").select();
		new SystemPasswordPromptDialog();
		new LabeledText("User ID:").setText(username);
		new LabeledText("Password (optional):").setText(password);
		new PushButton("OK").click();
		
		new WaitUntil(new RemoteSystemIsConnected(this));
	}

	/**
	 * Disconnect from Remote System.
	 */
	public void disconnect(){
		log.info("Disconnecting from remote system " + getLabel());
		select();
		new ContextMenu("Disconnect").select();
		new WaitWhile(new RemoteSystemIsConnected(this));
	}
	
	/**
	 * Check whether remote system is connected or not .
	 *
	 * @return true if Remote System is connected
	 */
	public boolean isConnected(){
		select();
		try{
			new ContextMenu("Disconnect");
		}
		catch(CoreLayerException e){
			return false;
		}
		return true;
	}
	
	/**
	 * Select.
	 */
	protected void select() {
		treeItem.select();
	}
	
	/**
	 * Delete Remote System.
	 */
	public void delete(){
		log.info("Deleting system " + getLabel());
		if(getLabel().equals("Local")){
			log.info("Local System cannot be deleted, skipping");
			return; 
		}
		select();
		if(isConnected())
			disconnect();
		new ContextMenu("Delete...").select();	
		new WaitUntil(new ShellWithTextIsAvailable("Delete Confirmation"),TimePeriod.NORMAL);
		new PushButton("Delete").click();
		new WaitWhile(new RemoteSystemExists(getLabel()), TIMEOUT);
		new WaitWhile(new JobIsRunning(), TIMEOUT);
	}
}
