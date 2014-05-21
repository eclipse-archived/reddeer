package org.jboss.reddeer.eclipse.datatools.ui.wizard;

import org.jboss.reddeer.eclipse.datatools.ui.DriverDefinition;
import org.jboss.reddeer.eclipse.datatools.ui.DriverTemplate;
import org.jboss.reddeer.eclipse.jface.wizard.WizardDialog;
import org.jboss.reddeer.swt.condition.ShellWithTextIsActive;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.wait.TimePeriod;
import org.jboss.reddeer.swt.wait.WaitWhile;

/**
 * Wizard for creating a new driver definition.
 * 
 * @author apodhrad
 * 
 */
public class DriverDefinitionWizard extends WizardDialog {

	public DriverDefinitionWizard() {
		super();
	}

	/**
	 * Create a given driver definition.
	 * 
	 * @param driverDefinition
	 */
	public void create(DriverDefinition driverDefinition) {
		DriverTemplate drvTemp = driverDefinition.getDriverTemplate();
		DriverDefinitionPage page = new DriverDefinitionPage();
		page.selectDriverTemplate(drvTemp.getType(), drvTemp.getVersion());
		page.setName(driverDefinition.getDriverName());
		page.addDriverLibrary(driverDefinition.getDriverLibrary());
		finish();
	}

	@Override
	public void finish() {
		new PushButton("OK").click();
		new WaitWhile(new ShellWithTextIsActive("New Driver Definition"), TimePeriod.NORMAL);
	}

}
