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
package org.eclipse.reddeer.core.handler;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.reddeer.common.util.Display;
import org.eclipse.reddeer.common.util.ResultRunnable;

/**
 * Contains methods for handling UI operations on {@link CTabFolder} widgets.
 * 
 * @author Lucia Jelinkova
 *
 */
public class CTabFolderHandler extends ControlHandler{
	
	private static CTabFolderHandler instance;
	
	/**
	 * Gets instance of CTabFolderHandler.
	 * 
	 * @return instance of CTabFolderHandler
	 */
	public static CTabFolderHandler getInstance(){
		if(instance == null){
			instance = new CTabFolderHandler();
		}
		return instance;
	}

	/**
	 * Sets focus on specified {@link CTabFolder}.
	 * 
	 * @param folder folder to handle
	 */
	public void setFocus(final CTabFolder folder) {
		Display.syncExec(new Runnable() {
			@Override
			public void run() {
				folder.forceFocus();
			}
		});
	}
	
	/**
	 * Gets tab items nested in specified {@link CTabFolder}.
	 * 
	 * @param tabFolder tab folder to handle
	 * @return tab items nested in specified tab folder
	 */
	public CTabItem[] getTabItems(final CTabFolder tabFolder) {
		return Display.syncExec(new ResultRunnable<CTabItem[]>() {
			@Override
			public CTabItem[] run() {
				return tabFolder.getItems();
			}
		});
	}
	
	/**
	 * Gets selected tab item 
	 * @param tabFolder to get selected item of
	 * @return selected tab item or null if none is selected
	 */
	public CTabItem getSelection(final CTabFolder tabFolder) {
		return Display.syncExec(new ResultRunnable<CTabItem>() {
			@Override
			public CTabItem run() {
				return tabFolder.getSelection();
			}
		});
	}
}


