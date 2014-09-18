package org.jboss.reddeer.eclipse.jdt.ui;

import org.jboss.reddeer.jface.wizard.NewWizardDialog;

/**
 * Represents new enum wizard
 * 
 * @author rawagner
 *
 */
public class NewEnumWizardDialog extends NewWizardDialog {

	/**
	 * Construct the wizard with Java > Enum.
	 */
	public NewEnumWizardDialog() {
		super("Java", "Enum");
		addWizardPage(new NewEnumWizardPage(), 0);
	}

	public NewEnumWizardPage getFirstPage() {
		return new NewEnumWizardPage();
	}

}
