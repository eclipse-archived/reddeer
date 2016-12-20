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
package org.jboss.reddeer.generator;

import static org.jboss.reddeer.generator.builder.MethodBuilder.method;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swtbot.generator.framework.WidgetUtils;
import org.jboss.reddeer.swt.generator.framework.rules.RedDeerUtils;
import org.jboss.reddeer.core.handler.WidgetHandler;
import org.jboss.reddeer.core.lookup.WidgetLookup;

/**
 * Code generator for text fields.
 * 
 * @author apodhrad
 *
 */
public class TextCodeGenerator implements CodeGenerator {

	@Override
	public boolean isSupported(Control control) {
		return control instanceof Text;
	}

	@Override
	public String getConstructor(Control control) {
		if (!isSupported(control)) {
			throw new IllegalArgumentException("Given " + control.getClass() + " is not supported");
		}
		String type = "LabeledText";
		String label = WidgetLookup.getInstance().getLabel(control);
		if (label == null || label.isEmpty()) {
			type = "DefaultText";
			label = String.valueOf(WidgetUtils.getIndex(control));
		} else {
			label = "\"" + label + "\"";
		}
		String ref = RedDeerUtils.getReferencedCompositeString(RedDeerUtils.getComposites(control));
		return "new " + type + "(" + ref + label + ")";
	}

	@Override
	public String getGeneratedCode(Control control) {
		if (isSupported(control)) {
			// TODO you could rather parse the constructor to get the label
			String label = WidgetLookup.getInstance().getLabel(control);
			if (label == null || label.isEmpty()) {
				return null;
			}
			return method().type("Text").get(label).returnCommand(getConstructor(control)).toString();
		}
		return null;
	}

}
