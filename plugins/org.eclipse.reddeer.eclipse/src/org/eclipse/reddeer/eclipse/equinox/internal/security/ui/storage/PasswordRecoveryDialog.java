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
package org.eclipse.reddeer.eclipse.equinox.internal.security.ui.storage;

import org.eclipse.reddeer.common.wait.WaitWhile;
import org.eclipse.reddeer.jface.dialogs.TitleAreaDialog;
import org.eclipse.reddeer.swt.condition.ShellIsAvailable;
import org.eclipse.reddeer.swt.impl.button.CancelButton;
import org.eclipse.reddeer.swt.impl.button.OkButton;
import org.eclipse.reddeer.swt.impl.group.DefaultGroup;
import org.eclipse.reddeer.swt.impl.text.DefaultText;

/**
 * Password hints dialog
 *
 * @author jnovak@redhat.com
 */
public class PasswordRecoveryDialog extends TitleAreaDialog {

	public static final String SHELL_TITLE = "Password Recovery";
	public static final String GROUP_1 = "Question 1";
	public static final String GROUP_2 = "Question 2";

	public PasswordRecoveryDialog() {
		super(SHELL_TITLE);
	}

	public void ok(){
		new OkButton().click();
		new WaitWhile(new ShellIsAvailable(SHELL_TITLE));
	}
	
	public void cancel(){
		new CancelButton().click();
		new WaitWhile(new ShellIsAvailable(SHELL_TITLE));
	}

	public void answerFirstQuestion(String answer) {
		DefaultGroup group = new DefaultGroup(GROUP_1);
		DefaultText answerField = new DefaultText(group, 0);
		answerField.setText(answer);
	}

	public void answerSecondQuestion(String answer) {
		DefaultGroup group = new DefaultGroup(GROUP_2);
		DefaultText answerField = new DefaultText(group, 0);
		answerField.setText(answer);
	}
}
