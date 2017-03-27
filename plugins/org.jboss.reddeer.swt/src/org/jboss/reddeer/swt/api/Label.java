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
package org.jboss.reddeer.swt.api;

/**
 * API for label manipulation.
 * 
 * @author Jiri Peterka
 *
 */
public interface Label extends Control<org.eclipse.swt.widgets.Label> {

	/**
	 * Returns the text of the label.
	 * 
	 * @return text of the label
	 */
	String getText();
}
