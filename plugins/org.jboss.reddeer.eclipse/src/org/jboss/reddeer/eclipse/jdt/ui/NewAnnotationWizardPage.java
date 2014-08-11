package org.jboss.reddeer.eclipse.jdt.ui;

import org.jboss.reddeer.jface.wizard.WizardPage;
import org.jboss.reddeer.swt.impl.button.CheckBox;
import org.jboss.reddeer.swt.impl.button.RadioButton;
import org.jboss.reddeer.swt.impl.text.LabeledText;

/**
 * Wizard page for creating an annotation.
 */
public class NewAnnotationWizardPage extends WizardPage {

	/**
	 * Sets a given package name.
	 * 
	 * @param packageName
	 *            Package name
	 */
	public void setPackage(String packageName) {
		new LabeledText("Package:").setText(packageName);
	}

	/**
	 * Sets a given source folder.
	 * 
	 * @param sourceFolder
	 *            Source folder
	 */
	public void setSourceFolder(String sourceFolder) {
		new LabeledText("Source folder:").setText(sourceFolder);
	}

	/**
	 * Sets a given enclosing type.
	 * 
	 * @param enclosing
	 *            Eclosing type
	 */
	public void setEnclosingType(boolean enclosing) {
		new CheckBox("Enclosing type:").toggle(enclosing);
	}

	/**
	 * Sets a given name.
	 * 
	 * @param name
	 *            Name
	 */
	public void setName(String name) {
		new LabeledText("Name:").setText(name);
	}

	/**
	 * Sets generating comments.
	 * 
	 * @param generate
	 *            Indicates whether to generate comments
	 */
	public void setGenerateComments(boolean generate) {
		new CheckBox("Generate comments").toggle(generate);
	}

	/**
	 * Sets public visibility
	 * 
	 * @param isPublic
	 *            Is public?
	 */
	public void setPublic(boolean isPublic) {
		new RadioButton("public").toggle(isPublic);
	}

	/**
	 * Sets default visibility.
	 * 
	 * @param isDefault
	 *            Is default?
	 */
	public void setDefault(boolean isDefault) {
		new RadioButton("default").toggle(isDefault);
	}

	/**
	 * Sets private visibility.
	 * 
	 * @param isPrivate
	 *            Is private?
	 */
	public void setPrivate(boolean isPrivate) {
		new RadioButton("private").toggle(isPrivate);
	}

	/**
	 * Sets protected visibility.
	 * 
	 * @param isProtected
	 *            Is protected?
	 */
	public void setProtected(boolean isProtected) {
		new RadioButton("protected").toggle(isProtected);
	}

}
