package org.jboss.reddeer.swt.impl.text;

import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.swt.api.Text;
import org.jboss.reddeer.swt.handler.TextHandler;
import org.jboss.reddeer.swt.handler.WidgetHandler;
import org.jboss.reddeer.swt.keyboard.KeyboardFactory;

/**
 * Abstract class for all Text implementations
 * @author Jiri Peterka
 *
 */
public abstract class AbstractText implements Text {
	
	protected org.eclipse.swt.widgets.Text w;
	private static final Logger log = Logger.getLogger(AbstractText.class);
	
	@Override
	public void setText(String str) {
		log.info("Set text " + str);
		TextHandler.getInstance().setText(w, str);
	}
	
	
	@Override
	public String getText() {
		String text = WidgetHandler.getInstance().getText(w);
		return text;
	}
	
	@Override
	public String getMessage() {
		return TextHandler.getInstance().getMessage(w);
	}
	
	@Override
	public String getToolTipText() {
		String tooltipText = WidgetHandler.getInstance().getToolTipText(w);
		return tooltipText;
	}

	@Override
	public void setFocus() {
		WidgetHandler.getInstance().setFocus(w);
	}
	
	public org.eclipse.swt.widgets.Text getSWTWidget(){
		return w;
	}
	
	@Override
	public boolean isEnabled() {
		return WidgetHandler.getInstance().isEnabled(w);
	}
	
	@Override
	public void typeText(String text) {
		log.info("Type text " + text);
		setText("");
		setFocus();
		KeyboardFactory.getKeyboard().type(text);
		
	}
	
	@Override
	public boolean isReadOnly(){
		return TextHandler.getInstance().isReadOnly(w);
	}
}
