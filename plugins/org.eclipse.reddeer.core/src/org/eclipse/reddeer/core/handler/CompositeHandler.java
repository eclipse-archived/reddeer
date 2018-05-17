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
package org.eclipse.reddeer.core.handler;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.reddeer.common.util.Display;
import org.eclipse.reddeer.common.util.ResultRunnable;

/**
 * Contains methods for handling UI operations on {@link Composite} widgets.
 * 
 * @author rawagner
 *
 */
public class CompositeHandler extends ControlHandler{
	
	private static CompositeHandler instance;
	
	/**
	 * Gets instance of CompositeHandler.
	 * 
	 * @return instance of CompositeHandler
	 */
	public static CompositeHandler getInstance(){
		if(instance == null){
			instance = new CompositeHandler();
		}
		return instance;
	}
	
	/**
	 * Gets children of composite
	 * @param composite to handle
	 * @return array of children of specified composite
	 */
	public Control[] getChildren(Composite composite){
		return Display.syncExec(new ResultRunnable<Control[]>() {

			@Override
			public Control[] run() {
				return composite.getChildren();
			}
		});
	}

}
