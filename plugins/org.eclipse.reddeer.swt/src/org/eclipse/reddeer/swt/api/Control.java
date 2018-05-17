/*******************************************************************************
 * Copyright (c) 2017, 2018 Red Hat, Inc and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Red Hat, Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.reddeer.swt.api;

/**
 * API for control manipulation
 * @author rawagner
 *
 * @param <T> extends swt Control
 */
public interface Control<T extends org.eclipse.swt.widgets.Control> extends Widget<T> {
	
	/**
	 * Finds out whether a control is enabled.
	 * 
	 * @return true if control is enabled, false otherwise
	 */
	boolean isEnabled();
	
	/**
	 * Checks if control is visible
	 * @return true if control is visible, false otherwise
	 */
	boolean isVisible();
	
	/**
	 * Sets focus to control
	 */
	void setFocus();
	
	/**
	 * Checks if control is focused
	 * @return true if control is focused, false otherwise
	 */
	boolean isFocusControl();
	
	/**
	 * Gets tooltip text of control
	 * @return tooltip text of control
	 */
	String getToolTipText();

	/**
	 * Returns a context menu associated to the control.
	 * 
	 * @return Context menu associated to the control
	 */
	Menu getContextMenu();
}
