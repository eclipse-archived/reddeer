/*******************************************************************************
 * Copyright (c) 2017 Red Hat, Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.reddeer.swt.test.impl.text;


import static org.junit.Assert.assertTrue;

import org.eclipse.reddeer.junit.runner.RedDeerSuite;
import org.eclipse.reddeer.swt.api.Text;
import org.eclipse.reddeer.swt.impl.button.PushButton;
import org.eclipse.reddeer.swt.impl.menu.ShellMenu;
import org.eclipse.reddeer.swt.impl.shell.DefaultShell;
import org.eclipse.reddeer.swt.impl.text.DefaultText;
import org.eclipse.reddeer.workbench.impl.view.WorkbenchView;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RedDeerSuite.class)
public class DefaultTextTest {

	@BeforeClass
	public static void openExplorer() {
		new WorkbenchView("RedDeer SWT Controls").open();
	}

	@Test
	public void defaultTextTest() {
		assertTrue(new DefaultText(0).getText().equals("Original text"));
		Text text = new DefaultText("Original text");
		assertTrue(text.getText().equals("Original text"));
		text.setText("New text");
		assertTrue(new DefaultText(0).getText().equals("New text"));
		assertTrue(text.getText().equals("New text"));
		assertTrue(new DefaultText("New text").getText().equals("New text"));
		text.setText("Original text");
	}
	
	@Test
	public void testInDialog() {
		new ShellMenu("File","New","Other...").select();
		new DefaultShell("New");
		Text t = new DefaultText(0);
		t.setText("myvalue");
		assertTrue(t.getText().equals("myvalue"));
		new PushButton("Cancel").click();
	}
}
