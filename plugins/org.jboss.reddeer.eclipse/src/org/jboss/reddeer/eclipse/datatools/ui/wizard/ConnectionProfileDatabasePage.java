package org.jboss.reddeer.eclipse.datatools.ui.wizard;

import org.jboss.reddeer.jface.wizard.WizardPage;
import org.jboss.reddeer.swt.impl.combo.LabeledCombo;

/**
 * An abstract wizard page for database connection profile.
 * 
 * IMPORTANT: It is assumed that the appropriate driver definition has been already created.
 * 
 * @author apodhrad
 * 
 */
public abstract class ConnectionProfileDatabasePage extends WizardPage {

	public static final String LABEL_DRIVER = "Drivers:";

	protected ConnectionProfileDatabasePage() {
		super();
	}

	/**
	 * Returns the driver name.
	 * 
	 * @return Driver name.
	 */
	public String getDriver() {
		return new LabeledCombo(LABEL_DRIVER).getText();
	}

	/**
	 * Sets the specified driver name.
	 * 
	 * @param driver
	 *            Driver name
	 */
	public void setDriver(String driver) {
		new LabeledCombo(LABEL_DRIVER).setSelection(driver);
	}

	/**
	 * Sets a given database.
	 * 
	 * @param database
	 *            Database
	 */
	public abstract void setDatabase(String database);

	/**
	 * Sets a given hostname.
	 * 
	 * @param hostname
	 *            Hostname
	 */
	public abstract void setHostname(String hostname);

	/**
	 * Sets a given port.
	 * 
	 * @param port
	 *            Port
	 */
	public abstract void setPort(String port);

	/**
	 * Sets a given user name.
	 * 
	 * @param username
	 *            User name
	 */
	public abstract void setUsername(String username);

	/**
	 * Sets a given password.
	 * 
	 * @param password
	 *            Password
	 */
	public abstract void setPassword(String password);
}
