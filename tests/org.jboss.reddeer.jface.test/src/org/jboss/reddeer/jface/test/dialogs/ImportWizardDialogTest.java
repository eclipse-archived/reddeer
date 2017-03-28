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
package org.jboss.reddeer.jface.test.dialogs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.jboss.reddeer.eclipse.topmenu.ImportMenuWizard;
import org.jboss.reddeer.jface.test.dialogs.impl.TestingImportWizard;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RedDeerSuite.class)
public class ImportWizardDialogTest {

	private ImportWizardDialogImpl importWizardDialog = new ImportWizardDialogImpl();

	@Test
	public void open() {
		importWizardDialog.open();

		String wizardDialogText = new DefaultShell().getText();
		assertThat(wizardDialogText, is(TestingImportWizard.NAME));
	}

	@After
	public void tearDown(){
		importWizardDialog.cancel();
	}
	
	private class ImportWizardDialogImpl extends ImportMenuWizard {

		public ImportWizardDialogImpl() {
			super(TestingImportWizard.NAME, TestingImportWizard.CATEGORY, TestingImportWizard.NAME);
		}
	}
}
