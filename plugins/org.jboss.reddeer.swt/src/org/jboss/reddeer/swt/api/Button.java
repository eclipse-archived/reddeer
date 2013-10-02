package org.jboss.reddeer.swt.api;

/**
 * API For Button (Push, Radio, Toggle) manipulation
 * @author Jiri Peterka
 *
 */
public interface Button {

	/**
	 * Performs click on a button
	 */
	void click();

	/**
	 * Return text on given Button
	 * @return
	 */
	String getText();
	/**
	 * Return tooltip of given Button
	 * @return
	 */
	String getToolTipText();
			
	boolean isEnabled();
}
