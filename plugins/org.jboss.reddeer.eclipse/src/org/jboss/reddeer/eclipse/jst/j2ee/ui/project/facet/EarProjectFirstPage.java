package org.jboss.reddeer.eclipse.jst.j2ee.ui.project.facet;

import org.jboss.reddeer.eclipse.wst.web.ui.wizards.DataModelFacetCreationWizardPage;
import org.jboss.reddeer.swt.impl.combo.DefaultCombo;
import org.jboss.reddeer.swt.impl.group.DefaultGroup;

/**
 * The first Wizard page for creating EAR project.
 */
public class EarProjectFirstPage extends DataModelFacetCreationWizardPage{
	
	/**
	 * Sets EAR verion.
	 * 
	 * @param version EAR version
	 */
	public void setEARVersion(String version){
		new DefaultCombo(new DefaultGroup("EAR version")).setSelection(version);
	}
	
	/**
	 * Returns EAR version.
	 * 
	 * @return EAR version
	 */
	public String getEARVersion(){
		return new DefaultCombo(new DefaultGroup("EAR version")).getSelection();
	}
	


}
