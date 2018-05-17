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
package org.eclipse.reddeer.eclipse.test.m2e.core.ui.preferences;

import static org.junit.Assert.assertTrue;

import org.eclipse.reddeer.eclipse.m2e.core.ui.preferences.WarningsPreferencePage;
import org.eclipse.reddeer.eclipse.m2e.core.ui.preferences.WarningsPreferencePage.MavenErrorSeverity;
import org.eclipse.reddeer.junit.runner.RedDeerSuite;
import org.eclipse.reddeer.workbench.ui.dialogs.WorkbenchPreferenceDialog;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test for MavenErrorPreferencePage
 * 
 * @author ldimaggi
 *
 */

@RunWith(RedDeerSuite.class)
public class MavenErrorPreferencePageTest {

	private WorkbenchPreferenceDialog preferencesDialog = new WorkbenchPreferenceDialog();	
	private WarningsPreferencePage mavenPreferencePage = new WarningsPreferencePage(preferencesDialog);

	@Test
	public void checkAllPreferences() {
		preferencesDialog.open();
		preferencesDialog.select(mavenPreferencePage);
				
		/* Change values - verify changes are made */
		mavenPreferencePage.setGroupId(MavenErrorSeverity.IGNORE);
		mavenPreferencePage.setVersion(MavenErrorSeverity.IGNORE);
		mavenPreferencePage.setProjectConfig(MavenErrorSeverity.IGNORE);
		mavenPreferencePage.setPluginExecution(MavenErrorSeverity.IGNORE);
		mavenPreferencePage.setManagedVersion(MavenErrorSeverity.IGNORE);
		
		assertTrue(mavenPreferencePage.getGroupId().equals(MavenErrorSeverity.IGNORE.getValue()));
		assertTrue(mavenPreferencePage.getVersion().equals(MavenErrorSeverity.IGNORE.getValue()));
		assertTrue(mavenPreferencePage.getProjectConfig().equals(MavenErrorSeverity.IGNORE.getValue()));
		assertTrue(mavenPreferencePage.getPluginExecution().equals(MavenErrorSeverity.IGNORE.getValue()));
		assertTrue(mavenPreferencePage.getManagedVersion().equals(MavenErrorSeverity.IGNORE.getValue()));
		
		preferencesDialog.cancel();
	}

	@After
	public void tearDown(){
		if(preferencesDialog.isOpen()){
			preferencesDialog.cancel();
		}
	}
}

