package org.jboss.reddeer.swt.handler;

import org.eclipse.swt.custom.StyledText;
import org.jboss.reddeer.swt.exception.SWTLayerException;
import org.jboss.reddeer.swt.util.Display;
import org.jboss.reddeer.swt.util.ResultRunnable;

/**
 * Contains methods for handling UI operations on {@link StyledText} widgets.
 * 
 * @author Jiri Peterka
 *
 */
public class StyledTextHandler {

	private static StyledTextHandler instance;

	private StyledTextHandler() {
	}

	/**
	 * Gets instance of StyledText.
	 * 
	 * @return instance of StyledText
	 */
	public static StyledTextHandler getInstance() {
		if (instance == null) {
			instance = new StyledTextHandler();
		}
		return instance;
	}

	/**
	 * Sets selection within specified {@link StyledText} to specified line and column.
	 * 
	 * @param styledText styled text to handle
	 * @param line line of selection
	 * @param column column of selection
	 */
	public void setSelectionWithinLine(final StyledText styledText, final int line,
			final int column) {

		Display.syncExec(new Runnable() {

			@Override
			public void run() {
				int offset = styledText.getContent().getOffsetAtLine(line)
						+ column;
				styledText.setSelection(offset);
			}
		});

	}
	
	/**
	 * Sets selection within specified {@link StyledText} to specified start and end.
	 * 
	 * @param styledText styled text to handle
	 * @param start selection start offset.
	 * @param end selection end offset
	 */
	public void setSelection(final StyledText styledText, final int start,
			final int end) {

		Display.syncExec(new Runnable() {

			@Override
			public void run() {
				styledText.setSelection(start,end);
			}
		});

	}

	/**
	 * Inserts specified text on the current position in specified {@link StyledText}.
	 * 
	 * @param styledText styled text to handle
	 * @param text text to insert
	 */
	public void insertText(final StyledText styledText, final String text) {
		Display.syncExec(new Runnable() {

			@Override
			public void run() {
				styledText.insert(text);
			}
		});
	}

	/**
	 * Inserts specified text on specified position in specified {@link StyledText}.
	 * 
	 * @param styledText styled text to handle
	 * @param line line to select
	 * @param column column to select
	 * @param text text to insert
	 */
	public void insertText(StyledText styledText, int line, int column,
			String text) {
		setSelectionWithinLine(styledText, line, column);
		insertText(styledText, text);
	}
	
	/**
	 * Gets position of first character of specified text in specified {@link StyledText}.
	 * 
	 * @param styledText styled text to handle
	 * @param text text to get position
	 * @return position of first character of specified text if exists, -1 otherwise 
	 */
	public int getPositionOfText(final StyledText styledText, final String text) {
		return Display.syncExec(new ResultRunnable<Integer>() {

			@Override
			public Integer run() {
				return styledText.getText().indexOf(text);
			}
		});
	}
	
	/**
	 * Selects first occurrence of specified text in specified {@link StyledText}.
	 * 
	 * @param styledText styled text to handle
	 * @param text to select
	 */
	public void selectText(final StyledText styledText, final String text) {
		final int position = getPositionOfText(styledText, text);
		if (position == -1) { 
			throw new SWTLayerException("Unable to find text " + text + " in styled text");
		}
		Display.syncExec(new Runnable() {

			@Override
			public void run() {
				styledText.setSelection(position, position + text.length());
			}
		});
	}

	/**
	 * Selects specified position in specified {@link StyledText}.
	 * 
	 * @param styledText styled text to handle
	 * @param position position to select in specified styled text
	 */
	public void selectPosition(final StyledText styledText, final int position) {
		Display.syncExec(new Runnable() {

			@Override
			public void run() {
				styledText.setSelection(position);
			}
		});
		
	}

	/**
	 * Gets selected text in specified {@link StyledText}.
	 * 
	 * @param styledText styled text to handle
	 * @return selected text
	 */
	public String getSelectionText(final StyledText styledText) {
		return Display.syncExec(new ResultRunnable<String>() {

			@Override
			public String run() {
				return styledText.getSelectionText();
			}
		});
	}
}
