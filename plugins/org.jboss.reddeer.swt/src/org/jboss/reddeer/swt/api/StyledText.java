package org.jboss.reddeer.swt.api;

import org.jboss.reddeer.swt.widgets.Widget;

/**
 * API for styled text manipulation.
 * 
 * @author Jiri Peterka
 *
 */
public interface StyledText extends Widget {

	/**
	 * Gets text of the styled text.
	 * 
	 * @return text of the styled text
	 */
	String getText();

	/**
	 * Sets text of the styled text.
	 * 
	 * @param text of the styled text to set
	 */
	void setText(String text);

	/**
	 * Gets ToolTip of the styled text.
	 * 
	 * @return ToolTip text of the styled text
	 */
	String getToolTipText();

	/**
	 * Inserts text into styled text at specified position.
	 * 
	 * @param line of position to insert
	 * @param column of position to insert
	 * @param text of the styled text to insert
	 */
	void insertText(int line, int column, String text);
	
	
	/**
     * Inserts text into styled text at current position or selection.
     * 
     * @param text to insert
     */
	void insertText(final String text);

	/**
	 * Returns position of text in the styled text. This is helpful in case 
	 * of inserting any text close to the position of queried text in the styled text.
	 * 
	 * @param text in the styled text
	 * @return position of first character of specified text or -1 if text was not found
	 */
	int getPositionOfText(String text);

	/**
	 * Selects specified text in styled text.
	 * 
	 * @param text to select
	 */
	void selectText(String text);

	/**
	 * Selects start position in the styled text.
	 * 
	 * @param position start position to select
	 */
	void selectPosition(int position);

	/**
	 * Gets selected text.
	 * 
	 * @return selected text
	 */
	String getSelectionText();
	
	/**
	 * Set the selection to a specified start and end.
	 * 
	 * @param start selection start offset.
	 * @param end selection end offset
	 */
	void setSelection(final int start, final int end);

	org.eclipse.swt.custom.StyledText getSWTWidget();
}
