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
package org.jboss.reddeer.workbench.condition;

import org.eclipse.swt.widgets.Control;
import org.jboss.reddeer.common.condition.AbstractWaitCondition;
import org.jboss.reddeer.common.util.Display;
import org.jboss.reddeer.common.util.ResultRunnable;
import org.jboss.reddeer.core.lookup.WidgetLookup;
import org.jboss.reddeer.workbench.core.lookup.WorkbenchPartLookup;

/**
 * Condition is met when active focused control is in active view.
 * 
 * @author Vlado Pakan
 *
 */
public class ActiveFocusControlIsInActiveView extends AbstractWaitCondition {
	
	/* (non-Javadoc)
	 * @see org.jboss.reddeer.common.condition.WaitCondition#test()
	 */
	@Override
	public boolean test() {
		// get active workbench part control (active view)
		final Control workbenchControl = WorkbenchPartLookup.getInstance()
				.getWorkbenchControl(WorkbenchPartLookup.getInstance().findActiveWorkbenchPartReference());
		
		// get focused control
		final Control focusedControl = WidgetLookup.getInstance().getFocusControl();
		Boolean result = Display.syncExec(new ResultRunnable<Boolean>() {
			@Override
			public Boolean run() {
				Control itParent = focusedControl;
				while (itParent != workbenchControl && itParent != null && !itParent.isDisposed()) {
					itParent = itParent.getParent();
				}
				return itParent != null;
			}
		});
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.reddeer.common.condition.AbstractWaitCondition#description()
	 */
	@Override
	public String description() {
		return "control has specified parent";
	}
}
