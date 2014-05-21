package org.jboss.reddeer.eclipse.datatools.ui.wizard;

import org.jboss.reddeer.eclipse.jface.wizard.WizardPage;
import org.jboss.reddeer.swt.impl.button.CheckBox;
import org.jboss.reddeer.swt.impl.combo.LabeledCombo;
import org.jboss.reddeer.swt.impl.text.DefaultText;

/**
 * A wizard page for creating new flat file data source profile.
 * 
 * @author apodhrad
 * 
 */
public class ConnectionProfileFlatFilePage extends WizardPage {

	public static final String PROFILE_NAME = "Flat File Data Source";

	public static final String LABEL_HOME_FOLDER = "Select home folder:";
	public static final String LABEL_CHARSET = "Select charset:";
	public static final String LABEL_STYLE = "Select flatfile style:";
	public static final String LABEL_FISRT_LINE_NAME = "Use first line as column name indicator.";
	public static final String LABEL_SECOND_LINE_TYPE = "Use second line as data type indicator.";

	public ConnectionProfileFlatFilePage() {
		super();
	}
	
	public void setHomeFolder(String folder) {
		new DefaultText(0).setText(folder);
	}

	public void setCharset(String charset) {
		new LabeledCombo(LABEL_CHARSET).setSelection(charset);
	}

	public void setStyle(String style) {
		new LabeledCombo(LABEL_STYLE).setSelection(style);
	}

	public void useFirstLineAsNameIndicator(boolean use) {
		useProperty(LABEL_FISRT_LINE_NAME, use);
	}

	public void useSecondLineAsTypeIndicator(boolean use) {
		useProperty(LABEL_SECOND_LINE_TYPE, use);
	}

	private void useProperty(String property, boolean use) {
		new CheckBox("property").toggle(use);
	}

}
