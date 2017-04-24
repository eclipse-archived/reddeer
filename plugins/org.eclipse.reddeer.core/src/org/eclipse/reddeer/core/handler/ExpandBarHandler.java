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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.reddeer.common.util.Display;
import org.eclipse.reddeer.common.util.ResultRunnable;

/**
 * Contains methods for handling UI operations on
 * {@link org.eclipse.swt.widgets.ExpandBar} widgets.
 * 
 * @author Vlado Pakan
 *
 */
public class ExpandBarHandler extends ControlHandler{

	private static ExpandBarHandler instance;
	
	/**
	 * Gets instance of ExpandBarHandler.
	 * 
	 * @return instance of ExpandBarHandler
	 */
	public static ExpandBarHandler getInstance(){
		if(instance == null){
			instance = new ExpandBarHandler();
		}
		return instance;
	}
	
	/**
	 * Gets SWT items nested in specified {@link org.eclipse.swt.widgets.ExpandBar}.
	 * 
	 * @param expandBar expand bar to handle
	 * @return list of nested expand items on specified expand bar
	 */
	public List<org.eclipse.swt.widgets.ExpandItem> getItems(
			final org.eclipse.swt.widgets.ExpandBar expandBar) {
		return Display.syncExec(new ResultRunnable<List<org.eclipse.swt.widgets.ExpandItem>>() {
			@Override
			public List<org.eclipse.swt.widgets.ExpandItem> run() {
				LinkedList<ExpandItem> result = new LinkedList<ExpandItem>();
				for (ExpandItem item: expandBar.getItems()) {
					result.addFirst(item);
				}
				return result;
				
			}
		});
	}
}
