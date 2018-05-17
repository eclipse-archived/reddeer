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
package org.eclipse.reddeer.eclipse.condition;

import org.eclipse.reddeer.common.condition.AbstractWaitCondition;
import org.eclipse.reddeer.eclipse.rse.ui.view.System;

/**
 * Returns true, if remote system with specified name isConnected
 * 
 * @author Pavol Srna
 *
 */
public class RemoteSystemIsConnected extends AbstractWaitCondition {

	private System system;
	
	/**
	 * Construct the condition with a given system.
	 * 
	 * @param system System
	 */
	public RemoteSystemIsConnected(System system) {
		this.system = system;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.reddeer.common.condition.WaitCondition#test()
	 */
	@Override
	public boolean test() {
			return system.isConnected();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.reddeer.common.condition.AbstractWaitCondition#description()
	 */
	@Override
	public String description() {
		return "remote system with name: " + this.system.getLabel() + "is connected";
	}
	
}
