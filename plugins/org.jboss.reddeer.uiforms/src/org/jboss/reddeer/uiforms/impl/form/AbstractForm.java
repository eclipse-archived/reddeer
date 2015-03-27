package org.jboss.reddeer.uiforms.impl.form;

import org.eclipse.swt.widgets.Control;
import org.hamcrest.Matcher;
import org.jboss.reddeer.core.handler.WidgetHandler;
import org.jboss.reddeer.core.reference.ReferencedComposite;
import org.jboss.reddeer.swt.widgets.AbstractWidget;
import org.jboss.reddeer.uiforms.api.Form;

/**
 * Common ancestor for all {@link Form} implementations. 
 * 
 * @author Lucia Jelinkova
 *
 */
public abstract class AbstractForm extends AbstractWidget<org.eclipse.ui.forms.widgets.Form> implements Form {

	protected AbstractForm(ReferencedComposite refComposite, int index, Matcher<?>... matchers){
		super(org.eclipse.ui.forms.widgets.Form.class, refComposite, index, matchers);
		setFocus();
	}
	
	@Override
	public Control getControl() {
		return swtWidget.getBody();
	}

	protected void setFocus() {
		WidgetHandler.getInstance().setFocus(swtWidget);
	}
	
	public String getText(){
		return WidgetHandler.getInstance().getText(swtWidget);
	}
}
