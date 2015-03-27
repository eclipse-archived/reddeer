package org.jboss.reddeer.swt.matcher;

import org.eclipse.swt.widgets.Widget;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.jboss.reddeer.core.exception.SWTLayerException;
import org.jboss.reddeer.core.handler.WidgetHandler;

/**
 * Matches widget with the specified style.
 * 
 * @author jjankovi
 * @author Radoslav Rabara
 * @deprecated since 0.8, use {@link #org.jboss.reddeer.core.matcher.WithStyleMatcher}
 */
@Deprecated
public class WithStyleMatcher extends BaseMatcher<Integer> {

	private int style; 
	
	/**
	 * Constructs matcher matching widgets with the specified style
	 * 
	 * @param style style of the matching widget 
	 * 
	 */
	public WithStyleMatcher(int style) {
		this.style = style;
	}
	
	@Override
	public boolean matches(Object item) {
		if (item instanceof Widget){
			try {
				Integer widgetStyle = WidgetHandler.getInstance().getStyle((Widget)item);
				return (widgetStyle.intValue() & style) != 0;
			} catch (SWTLayerException sle) {
				// object is not supported by widget handler mechanism 'getStyle'
				return false;
			}
		}
		return false;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("has style " + style);
	}
	
	@Override
	public String toString() {
		return "Widget matcher matching widgets with style: " + style;
	}
}
