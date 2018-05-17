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
package org.eclipse.reddeer.eclipse.datatools.enablement.oracle.ui;

import org.eclipse.reddeer.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;
import org.eclipse.reddeer.jface.wizard.WizardDialog;

/**
 * A wizard page for creating new Oracle database profile.
 * 
 * 
 * @author apodhrad, mlabuda@redhat.com
 * 
 */
public class OracleDBProfileDetailsWizardPage extends ExtensibleProfileDetailsWizardPage {

	public static final String PROFILE_NAME = "Oracle";

	private static final String LABEL_DATABASE = "SID:";
	private static final String LABEL_HOSTNAME = "Host:";
	private static final String LABEL_URL = "Connection URL:";
	private static final String LABEL_USER_NAME = "User name:";
	private static final String LABEL_PORT_NUMBER = "Port number:";
	private static final String LABEL_PASSWORD = "Password:";
	private static final String LABEL_SAVE_PASSWORD = "Save password";

	/**
	 * Instantiates a new connection profile oracle page.
	 */
	public OracleDBProfileDetailsWizardPage(WizardDialog dialog) {
		super(dialog);
	}

	@Override
	protected String getHostnameLabel() {
		return LABEL_HOSTNAME;
	}
	
	@Override
	protected String getDatabaseLabel() {
		return LABEL_DATABASE;
	}
	
	@Override
	protected String getURLLabel() {
		return LABEL_URL;
	}
	
	@Override
	protected String getUsernameLabel() {
		return LABEL_USER_NAME;
	}
	
	@Override
	protected String getPortLabel() {
		return LABEL_PORT_NUMBER;
	}
	
	@Override
	protected String getPasswordLabel() {
		return LABEL_PASSWORD;
	}
	
	@Override
	protected String getSavePasswordLabel() {
		return LABEL_SAVE_PASSWORD;
	}
}
