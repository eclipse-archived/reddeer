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
package org.jboss.reddeer.eclipse.jst.ejb.ui.project.facet;

import org.jboss.reddeer.eclipse.topmenu.NewMenuWizard;

/**
 * This class represents EJB Wizard dialog.
 * @author rawagner
 *
 */
public class EjbProjectWizard extends NewMenuWizard {
	
	public static final String CATEGORY="EJB";
	public static final String NAME="EJB Project";
	/**
	 * Default constructor.
	 */
	public EjbProjectWizard() {
		super("New EJB Project",CATEGORY, NAME);
	}

}
