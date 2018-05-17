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
package org.eclipse.reddeer.workbench.condition;

import org.eclipse.reddeer.common.condition.AbstractWaitCondition;
import org.eclipse.reddeer.workbench.impl.editor.TextEditor;

/**
 * Check if editor contains specified text
 * @author rawagner
 *
 */
public class TextEditorContainsText extends AbstractWaitCondition{
	
	private String text;
	private TextEditor editor;
	private String resultText;
	
	/**
	 * Default constructor
	 * @param editor to check
	 * @param text editor should contain
	 */
	public TextEditorContainsText(TextEditor editor, String text) {
		this.editor = editor;
		this.text = text;
	}

	@Override
	public boolean test() {
		if (editor.getText().contains(text)) {
			this.resultText = text;
			return true;
		}
		return false;
	}

	@Override
	public String description() {
		return "Editor '"+editor.getTitle()+"' contains text '"+text+"'";
	}

	@SuppressWarnings("unchecked")
	@Override 
	public String getResult() {
		return this.resultText;
	}
	
}
