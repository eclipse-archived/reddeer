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
package org.eclipse.reddeer.jface.api;

import org.eclipse.reddeer.core.reference.ReferencedComposite;
import org.eclipse.reddeer.jface.window.Openable;
import org.eclipse.reddeer.swt.api.Shell;

/**
 * API for Window manipulation
 * @author rawagner
 *
 */
public interface Window extends ReferencedComposite{
	
	/**
	 * Gets the control.
	 *
	 * @return the control
	 */
	Shell getShell();
	
	/**
	 * Returns Eclipse counterpart class
	 * 
	 * @return Eclipse counterpart class
	 */
	Class<? extends org.eclipse.jface.window.Window> getEclipseClass();
	
	/**
	 * Sets shell which is encapsulated by window
	 * @param shell to set
	 */
	void setShell(Shell shell);

	/**
	 * Opens the window
	 * 
	 * @return the opened window
	 */
	Window open();

	/**
	 * Checks if window if open
	 * @return true if window is open, false otherwise
	 */
	boolean isOpen();
	
	/**
	 * Sets open action to be used when {@link #open()} is called. Set to null if {@link #getDefaultOpenAction()} should be used.
	 */
	void setOpenAction(Openable openAction);
	
	/**
	 * Returns default open action or null if there is none
	 * @return default open action
	 */
	Openable getDefaultOpenAction();

}
