package org.jboss.reddeer.eclipse.jdt.ui;

import org.jboss.reddeer.eclipse.jface.wizard.NewWizardDialog;

/**
 * Represents new enum wizard
 * @author rawagner
 *
 */
public class NewEnumWizardDialog extends NewWizardDialog{
	
	public NewEnumWizardDialog() {
		super("Java", "Enum");
		addWizardPage(new NewEnumWizardPage(), 0);
	}
	
	@Override
	public NewEnumWizardPage getFirstPage() {
		return new NewEnumWizardPage();
	}

}
