package org.jboss.reddeer.swt.test.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextTestUtils {

	public static Text createText(final Shell parent, final String initialText){
		final Text text = new Text(parent, SWT.LEFT);
		text.setSize(200,30);
		text.setText(initialText);
		return text;
	}
}
