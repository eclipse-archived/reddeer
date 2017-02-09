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
package org.jboss.reddeer.eclipse.rse.ui.wizard;

import java.util.LinkedList;
import java.util.List;

import org.jboss.reddeer.jface.wizard.WizardPage;
import org.jboss.reddeer.swt.api.Combo;
import org.jboss.reddeer.swt.impl.combo.DefaultCombo;
import org.jboss.reddeer.swt.impl.text.DefaultText;


/**
 * This class represents the main wizard page of the New Connection wizard dialog where
 * the host name and connection name of the remote system are set.
 * @author Pavol Srna
 *
 */
public class NewConnectionWizardMainPage extends WizardPage {

	/**
	 * Sets Host name.
	 *
	 * @param hostname the new host name
	 */
	public void setHostName(String hostname){
		getHostNameCombo().setText(hostname);
	}

	/**
	 * Sets Connection name.
	 *
	 * @param name the new connection name
	 */
	public void setConnectionName(String name){
		new DefaultText(0).setText(name);
	}
	
	/**
	 * Gets list of all defined host names.
	 *
	 * @return list of host names
	 */
	public List<String> getHostNames() {
		List<String> items = new LinkedList<String>(getHostNameCombo().getItems());
		return items;
	}
	
	private Combo getHostNameCombo(){
		return new DefaultCombo(1);
	}	
}