package org.jboss.reddeer.eclipse.test.datatools.ui;

import java.io.File;

import org.jboss.reddeer.eclipse.datatools.ui.DriverDefinition;
import org.jboss.reddeer.eclipse.datatools.ui.DriverTemplate;
import org.jboss.reddeer.eclipse.datatools.ui.preference.DriverDefinitionPreferencePage;
import org.jboss.reddeer.eclipse.jdt.ui.WorkbenchPreferenceDialog;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.jboss.reddeer.swt.impl.table.DefaultTable;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 
 * @author Andrej Podhradsky (andrej.podhradsky@gmail.com)
 * 
 */
@RunWith(RedDeerSuite.class)
public class DriverDefinitionTest {

	@Test
	public void driverDefinitionTest() {
		WorkbenchPreferenceDialog dialog = new WorkbenchPreferenceDialog();
		dialog.open();
		
		DriverDefinitionPreferencePage preferencePage = new DriverDefinitionPreferencePage();
		dialog.select(preferencePage);
		preferencePage.addDriverDefinition().create(createTestDriverDefinition());
		// test if a driver was successfully created
		new DefaultTable().getItem("Test HSLQDB Driver");
		dialog.ok();
	}

	private DriverDefinition createTestDriverDefinition() {
		DriverDefinition dd = new DriverDefinition();
		dd.setDriverTemplate(new DriverTemplate("HSQLDB JDBC Driver", "1.8"));
		dd.setDriverName("Test HSLQDB Driver");
		dd.setDriverLibrary(new File("target/lib/hsqldb-1.8.0.10.jar").getAbsolutePath());
		return dd;
	}
}
