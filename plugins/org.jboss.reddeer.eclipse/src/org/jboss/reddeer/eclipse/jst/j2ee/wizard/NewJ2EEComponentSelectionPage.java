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
package org.jboss.reddeer.eclipse.jst.j2ee.wizard;

import org.jboss.reddeer.common.wait.AbstractWait;
import org.jboss.reddeer.common.wait.TimePeriod;
import org.jboss.reddeer.common.wait.WaitUntil;
import org.jboss.reddeer.common.wait.WaitWhile;
import org.jboss.reddeer.eclipse.jst.servlet.ui.WebProjectWizard;
import org.jboss.reddeer.jface.wizard.WizardPage;
import org.jboss.reddeer.swt.condition.WidgetIsEnabled;
import org.jboss.reddeer.swt.impl.button.CheckBox;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.button.RadioButton;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.impl.text.DefaultText;
import org.jboss.reddeer.workbench.core.condition.JobIsRunning;

public class NewJ2EEComponentSelectionPage extends WizardPage{
	
	/**
	 * Toggle create default modules.
	 *
	 * @param toggle the toggle
	 */
	public void toggleCreateDefaultModules(boolean toggle){
		new CheckBox("Create default modules").toggle(toggle);
	}
	
	/**
	 * Checks if is creates the default modules.
	 *
	 * @return true, if is creates the default modules
	 */
	public boolean isCreateDefaultModules(){
		return new CheckBox("Create default modules").isChecked();
	}
	
	/**
	 * Opens new EJB project dialog - not yet supported in eclipse layer
	 * use swt layer in following steps.
	 */
	public void addEnterpriseJavaBean(){
		new RadioButton("Enterprise Java Bean").click();
		new PushButton("Next >").click();
		//should return wizard when it will be implemented
	}
	
	/**
	 * Adds the web.
	 *
	 * @return the web project wizard
	 */
	public WebProjectWizard addWeb(){
		new RadioButton("Web").click();
		new PushButton("Next >").click();
		return new WebProjectWizard();
	}
	
	/**
	 * Opens new Connector project dialog - not yet supported in eclipse layer
	 * use swt layer in following steps.
	 */
	public void addConnector(){
		new RadioButton("Connector").click();
		new PushButton("Next >").click();
		//should return wizard when it will be implemented
	}
	
	/**
	 * Opens new Application Client project dialog - not yet supported in eclipse layer
	 * use swt layer in following steps.
	 */
	public void addApplicationClient(){
		new RadioButton("Application Client").click();
		new PushButton("Next >").click();
		//should return wizard when it will be implemented
	}
	
	/**
	 * Toggle application client module.
	 *
	 * @param toggle the toggle
	 */
	public void toggleApplicationClientModule(boolean toggle){
		new CheckBox("Application client module").toggle(toggle);
	}
	
	/**
	 * Toggle ejb module.
	 *
	 * @param toggle the toggle
	 */
	public void toggleEJBModule(boolean toggle){
		new CheckBox("EJB module").toggle(toggle);
	}
	
	/**
	 * Toggle web module.
	 *
	 * @param toggle the toggle
	 */
	public void toggleWebModule(boolean toggle){
		new CheckBox("Web module").toggle(toggle);
	}
	
	/**
	 * Toggle connection module.
	 *
	 * @param toggle the toggle
	 */
	public void toggleConnectionModule(boolean toggle){
		new CheckBox("Connector module").toggle(toggle);
	}
	
	/**
	 * Checks if is application client module.
	 *
	 * @return true, if is application client module
	 */
	public boolean isApplicationClientModule(){
		return new CheckBox("Application client module").isChecked();
	}
	
	/**
	 * Checks if is EJB module.
	 *
	 * @return true, if is EJB module
	 */
	public boolean isEJBModule(){
		return new CheckBox("EJB module").isChecked();
	}
	
	/**
	 * Checks if is web module.
	 *
	 * @return true, if is web module
	 */
	public boolean isWebModule(){
		return new CheckBox("Web module").isChecked();
	}
	
	/**
	 * Checks if is connection module.
	 *
	 * @return true, if is connection module
	 */
	public boolean isConnectionModule(){
		return new CheckBox("Connector module").isChecked();
	}
	
	/**
	 * Sets the application client module name.
	 *
	 * @param name the new application client module name
	 */
	public void setApplicationClientModuleName(String name){
		new DefaultText(0).setText(name);
	}
	
	/**
	 * Sets the EJB module name.
	 *
	 * @param name the new EJB module name
	 */
	public void setEJBModuleName(String name){
		new DefaultText(1).setText(name);
	}
	
	/**
	 * Sets the web module name.
	 *
	 * @param name the new web module name
	 */
	public void setWebModuleName(String name){
		new DefaultText(2).setText(name);
	}
	
	/**
	 * Sets the connector module name.
	 *
	 * @param name the new connector module name
	 */
	public void setConnectorModuleName(String name){
		new DefaultText(3).setText(name);
	}
	
	/**
	 * Gets the application client module name.
	 *
	 * @return the application client module name
	 */
	public String getApplicationClientModuleName(){
		return new DefaultText(0).getText();
	}
	
	/**
	 * Gets the EJB module name.
	 *
	 * @return the EJB module name
	 */
	public String getEJBModuleName(){
		return new DefaultText(1).getText();
	}
	
	/**
	 * Gets the web module name.
	 *
	 * @return the web module name
	 */
	public String getWebModuleName(){
		return new DefaultText(2).getText();
	}
	
	/**
	 * Gets the connector module name.
	 *
	 * @return the connector module name
	 */
	public String getConnectorModuleName(){
		return new DefaultText(3).getText();
	}
	

	/**
	 * Finish.
	 */
	public void finish(){
		new WaitUntil(new WidgetIsEnabled(new PushButton("Finish")));
		new PushButton("Finish").click();
		new WaitWhile(new JobIsRunning());
		new DefaultShell("New EAR Application Project");
		//have to wait otherwise SWT exception is thrown
		AbstractWait.sleep(TimePeriod.getCustom(3));
	}
	
	
}
