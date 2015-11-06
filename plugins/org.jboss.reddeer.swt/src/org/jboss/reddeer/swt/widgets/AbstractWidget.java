package org.jboss.reddeer.swt.widgets;

import org.hamcrest.Matcher;
import org.jboss.reddeer.core.handler.WidgetHandler;
import org.jboss.reddeer.core.lookup.WidgetLookup;
import org.jboss.reddeer.core.reference.ReferencedComposite;
import org.jboss.reddeer.swt.exception.SWTLayerException;

/**
 * Abstract widget implementation that looks up and stores swt widget. 
 *  
 * @author Lucia Jelinkova
 *
 * @param <T>
 */
public abstract class AbstractWidget<T extends org.eclipse.swt.widgets.Widget> implements Widget {

	protected T swtWidget;
	
	protected AbstractWidget(T swtWidget) {
		if (swtWidget == null){
			throw new SWTLayerException("SWT widget provided is null");
		}
		if (swtWidget.isDisposed()){
			throw new SWTLayerException("SWT widget provided is disposed");
		}
		this.swtWidget = swtWidget;
	}
	
	protected AbstractWidget(Class<T> widgetClass, ReferencedComposite refComposite, int index, Matcher<?>... matchers) {
		swtWidget = WidgetLookup.getInstance().activeWidget(refComposite, widgetClass, index, matchers);
	}
	
	@Override
	public T getSWTWidget() {
		return swtWidget;
	}

	@Override
	public boolean isEnabled() {
		return WidgetHandler.getInstance().isEnabled(swtWidget);
	}
	
	@Override
	public boolean isDisposed() {
		return WidgetHandler.getInstance().isDisposed(swtWidget);
	}
}
