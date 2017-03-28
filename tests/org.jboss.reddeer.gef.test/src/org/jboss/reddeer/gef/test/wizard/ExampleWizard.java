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
package org.jboss.reddeer.gef.test.wizard;

import org.jboss.reddeer.eclipse.topmenu.NewMenuWizard;
import org.jboss.reddeer.eclipse.ui.dialogs.NewWizard;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.impl.text.LabeledText;
import org.jboss.reddeer.swt.impl.tree.DefaultTreeItem;

/**
 * 
 * @author Andrej Podhradsky (andrej.podhradsky@gmail.com)
 *
 */
public class ExampleWizard extends NewMenuWizard {

	public static final String FLOW_DIAGRAM = "Flow Diagram";
	public static final String FLOW_SUFFIX = ".flow";
	public static final String LOGIC_DIAGRAM = "Logic Diagram";
	public static final String LOGIC_SUFFIX = ".logic";
	public static final String SHAPES_DIAGRAM = "Shapes Diagram";
	public static final String SHAPES_SUFFIX = ".shapes";

	private String example;

	public ExampleWizard(String example) {
		super("","Examples", example);
		this.example = example;
	}

	@Override
	public void open() {
		if(!isOpen()){
			log.info("Opening wizard using top menu ");
			NewWizard nw = new NewWizard();
			nw.open();
			try {
				new DefaultTreeItem("Examples", example).select();
			} catch (Exception e) {
				new DefaultTreeItem("Examples", "GEF (Graphical Editing Framework)", example).select();
			}
			nw.next();
			setShell(new DefaultShell(matcher));
		}
	}

	public void setName(String name) {
		new LabeledText("File name:").setText(name);
	}

	public static void createFlowDiagram(String name) {
		ExampleWizard wizard = new ExampleWizard(FLOW_DIAGRAM);
		wizard.open();
		if (!name.endsWith(FLOW_SUFFIX)) {
			name += FLOW_SUFFIX;
		}
		wizard.setName(name);
		wizard.finish();
	}

	public static void createLogicDiagram(String name) {
		ExampleWizard wizard = new ExampleWizard(LOGIC_DIAGRAM);
		wizard.open();
		if (!name.endsWith(LOGIC_SUFFIX)) {
			name += LOGIC_SUFFIX;
		}
		wizard.setName(name);
		wizard.finish();
	}

	public static void createShapesDiagram(String name) {
		ExampleWizard wizard = new ExampleWizard(SHAPES_DIAGRAM);
		wizard.open();
		if (!name.endsWith(SHAPES_SUFFIX)) {
			name += SHAPES_SUFFIX;
		}
		wizard.setName(name);
		wizard.finish();
	}
}
