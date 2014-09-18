package org.jboss.reddeer.swt.handler;

import org.jboss.reddeer.swt.util.Display;
import org.jboss.reddeer.swt.util.ResultRunnable;

/**
 * Contains methods for handling UI operations on
 * {@link org.eclipse.swt.widgets.Scale} widgets.
 * 
 * @author Vlado Pakan
 *
 */
public class ScaleHandler {

	private static ScaleHandler instance;

	private ScaleHandler() {

	}

	/**
	 * Gets instance of ScaleHandler.
	 * 
	 * @return instance of ScaleHandler
	 */
	public static ScaleHandler getInstance() {
		if (instance == null) {
			instance = new ScaleHandler();
		}
		return instance;
	}

	/**
	 * Gets minimum value of specified {@link org.eclipse.swt.widgets.Scale}.
	 * 
	 * @param scale scale to handle
	 * @return minimum value of specified scale
	 */
	public int getMinimum(final org.eclipse.swt.widgets.Scale scale) {
		return Display.syncExec(new ResultRunnable<Integer>() {
			@Override
			public Integer run() {
				return scale.getMinimum();
			}
		});
	}

	/**
	 * Gets maximum value of specified {@link org.eclipse.swt.widgets.Scale}.
	 * 
	 * @param scale scale to handle
	 * @return maximum value of specified scale
	 */
	public int getMaximum(final org.eclipse.swt.widgets.Scale scale) {
		return Display.syncExec(new ResultRunnable<Integer>() {
			@Override
			public Integer run() {
				return scale.getMaximum();
			}
		});
	}

	/**
	 * Gets current value of specified {@link org.eclipse.swt.widgets.Scale}.
	 * 
	 * @param scale scale to handle
	 * @return current value of specified scale
	 */
	public int getSelection(final org.eclipse.swt.widgets.Scale scale) {
		return Display.syncExec(new ResultRunnable<Integer>() {
			@Override
			public Integer run() {
				return scale.getSelection();
			}
		});
	}

	/**
	 * Sets specified {@link org.eclipse.swt.widgets.Scale} to specified value.
	 * 
	 * @param scale scale to handle
	 * @param value value to set
	 */
	public void setSelection(final org.eclipse.swt.widgets.Scale scale,
			final int value) {
		Display.syncExec(new Runnable() {
			@Override
			public void run() {
				scale.setSelection(value);
			}
		});
		WidgetHandler.getInstance().sendClickNotifications(scale);
	}
}
