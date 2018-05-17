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
package org.eclipse.reddeer.eclipse.datatools.connectivity.ui.dialogs;

import org.eclipse.reddeer.common.logging.Logger;
import org.eclipse.reddeer.common.util.Display;
import org.eclipse.reddeer.common.wait.WaitUntil;
import org.eclipse.reddeer.common.wait.WaitWhile;
import org.eclipse.reddeer.eclipse.datatools.ui.DriverDefinition;
import org.eclipse.reddeer.eclipse.datatools.ui.DriverTemplate;
import org.eclipse.reddeer.swt.api.Tree;
import org.eclipse.reddeer.swt.api.TreeItem;
import org.eclipse.reddeer.swt.condition.ShellIsAvailable;
import org.eclipse.reddeer.swt.impl.button.OkButton;
import org.eclipse.reddeer.swt.impl.button.PushButton;
import org.eclipse.reddeer.swt.impl.button.RadioButton;
import org.eclipse.reddeer.swt.impl.list.DefaultList;
import org.eclipse.reddeer.swt.impl.shell.DefaultShell;
import org.eclipse.reddeer.swt.impl.tab.DefaultTabItem;
import org.eclipse.reddeer.swt.impl.text.DefaultText;
import org.eclipse.reddeer.swt.impl.text.LabeledText;
import org.eclipse.reddeer.swt.impl.tree.DefaultTree;
import org.eclipse.reddeer.swt.impl.tree.DefaultTreeItem;

/**
 * Wizard for creating a new driver definition.
 * 
 * @author apodhrad
 * 
 */
public class DriverDialog extends DefaultShell {
	
	public static final String WIZARD_TITLE = "New Driver Definition";
	public static final String LABEL_DRIVER_CLASS = "Driver Class";
	public static final String LABEL_DRIVER_NAME = "Driver name:";
	public static final String TAB_NAME_TYPE = "Name/Type";
	public static final String TAB_PROPERTIES = "Properties";
	public static final String TAB_JAR_LIST = "JAR List";
	public static final String BUTTON_CLEAR_ALL = "Clear All";
	public static final String BUTTON_REMOVE_JAR = "Remove JAR/Zip";
	protected final Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * Instantiates a new driver definition wizard.
	 */
	public DriverDialog() {
		super();
	}

	/**
	 * Create a given driver definition.
	 *
	 * @param driverDefinition the driver definition
	 */
	public void create(DriverDefinition driverDefinition) {
		DriverTemplate drvTemp = driverDefinition.getDriverTemplate();
		selectDriverTemplate(drvTemp.getType(), drvTemp.getVersion());
		setName(driverDefinition.getDriverName());
		addDriverLibrary(driverDefinition.getDriverLibrary());
		ok();
	}

	public void ok() {
		new OkButton().click();
		new WaitWhile(new ShellIsAvailable(this));
	}

	/**
	 * Set a driver name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		new LabeledText(LABEL_DRIVER_NAME).setText(name);
	}

	/**
	 * Select a driver template.
	 *
	 * @param type the type
	 * @param version the version
	 */
	public void selectDriverTemplate(String type, String version) {
		selectTab(TAB_NAME_TYPE);
		Tree tree = new DefaultTree();
		// Database
		TreeItem root = tree.getItems().get(0);
		for (TreeItem item : root.getItems()) {
			if (type.equals(item.getCell(0)) && version.equals(item.getCell(2))) {
				item.select();
				break;
			}
		}
	}

	/**
	 * Sets a given driver class. Not yet implemented!
	 * 
	 * @param driverClass
	 *            Driver class
	 */
	public void setDriverClass(String driverClass) {
		selectTab(TAB_PROPERTIES);
		new DefaultTreeItem("General","Driver Class").doubleClick();
		new PushButton("...").click();
		String dlgTitle = "Available Classes from Jar List";
		new WaitUntil(new ShellIsAvailable(dlgTitle));
		new DefaultShell(dlgTitle);
		new RadioButton(0).click();
		new DefaultText("").setText(driverClass);
		new OkButton().click();		
	}

	/**
	 * Add a driver library.
	 * 
	 * @param driverLocation
	 *            an absolute path to the driver lib
	 */
	public void addDriverLibrary(String driverLocation) {
		selectTab(TAB_JAR_LIST);
		clearAllDriverLibraries();
		addItem(driverLocation);
		addItem(driverLocation);
		removeDriverLibrary(driverLocation);
	}

	/**
	 * Remove a given library.
	 *
	 * @param driverLocation the driver location
	 */
	public void removeDriverLibrary(String driverLocation) {
		new DefaultList().select(driverLocation);
		new PushButton(BUTTON_REMOVE_JAR).click();
	}

	/**
	 * Remove all listed libraries.
	 */
	public void clearAllDriverLibraries() {

		if (new DefaultList().getListItems().length > 0) {			
			new PushButton(BUTTON_CLEAR_ALL).click();
		} else {
			log.info("No drivers to clean, skipped");
		}			
	}

	/**
	 * Select a given tab.
	 *
	 * @param label the label
	 */
	public void selectTab(String label) {
		new DefaultTabItem(label).activate();
	}

	/**
	 * Directly add an item to the list
	 * 
	 * @param item
	 */
	private void addItem(String item) {
		new ExtendedDeafultList().addItem(item);
	}

	/**
	 * DefaultList extended by directly adding an item to the list
	 * 
	 * @author apodhrad
	 *
	 */
	private class ExtendedDeafultList extends DefaultList {

		public ExtendedDeafultList() {
			super();
		}

		public void addItem(final String item) {
			Display.syncExec(new Runnable() {

				@Override
				public void run() {
					swtWidget.add(item);
				}
			});
		}
	}

}
