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
import org.eclipse.reddeer.eclipse.debug.ui.views.launch.TerminateButton;

/**
 * A wait condition which detects whether a debugging is terminated. It returns
 * true if the Terminate button is disabled.
 * 
 * @author Andrej Podhradsky
 *
 */
public class LaunchIsTerminated extends AbstractWaitCondition {

	private TerminateButton terminateButton;

	/* (non-Javadoc)
	 * @see org.eclipse.reddeer.common.condition.WaitCondition#test()
	 */
	@Override
	public boolean test() {
		if (terminateButton == null) {
			terminateButton = new TerminateButton();
		}
		return !terminateButton.isEnabled();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.reddeer.common.condition.AbstractWaitCondition#description()
	 */
	@Override
	public String description() {
		return "Tool item with tooltip 'Terminate' is still enabled";
	}

}
