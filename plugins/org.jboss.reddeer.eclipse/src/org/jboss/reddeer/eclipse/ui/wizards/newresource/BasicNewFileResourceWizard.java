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
package org.jboss.reddeer.eclipse.ui.wizards.newresource;

import org.jboss.reddeer.eclipse.topmenu.NewMenuWizard;
/**
 * Represents new file creation wizard dialog (General &gt; File)
 * 
 * @author jjankovi
 *
 */
public class BasicNewFileResourceWizard extends NewMenuWizard {
	/**
	 * Constructs the wizard with "General" &gt; "File".
	 */
	public BasicNewFileResourceWizard() {
		super("New File","General", "File");
	}
}
