/******************************************************************************* 
 * Copyright (c) 2016 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.reddeer.core.handler;

import java.lang.reflect.Field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.common.util.Display;
import org.jboss.reddeer.common.util.ResultRunnable;

/**
 * Contains methods for handling UI operations on {@link CTabItem} widgets.
 * 
 * @author Vlado Pakan
 *
 */
public class CTabItemHandler extends ItemHandler{
	private static final Logger logger = Logger.getLogger(CTabItemHandler.class);
	private static CTabItemHandler instance;
	
	/**
	 * Gets instance of CTabItemHandler.
	 * 
	 * @return instance of CTabItemHandler
	 */
	public static CTabItemHandler getInstance(){
		if(instance == null){
			instance = new CTabItemHandler();
		}
		return instance;
	}
	/**
	 * Gets {@link CTabFolder} containing specified {@link CTabItem}.
	 * 
	 * @param swtCTabItem CTab item to handle
	 * @return CTabFolder containing specified CTabItem
	 */
	public CTabFolder getCTabFolder(final CTabItem swtCTabItem) {
		return Display
				.syncExec(new ResultRunnable<org.eclipse.swt.custom.CTabFolder>() {
					@Override
					public CTabFolder run() {
						return swtCTabItem.getParent();
					}
				});
	}
	
	/**
	 * Gets the control that is displayed in the content area of the tab item.
	 * @param ctabItem item to handler
	 * @return control that is displayed in the content area of the tab item
	 */
	public Control getControl(final CTabItem ctabItem){
		return Display.syncExec(new ResultRunnable<Control>() {
			
			@Override
			public Control run() {
				return ctabItem.getControl();
			}
		});
	}

	/**
	 * Creates the event of specified type for specified {@link CTabItem}.
	 * 
	 * @param swtCTabItem CTab item to handle
	 * @param type type of the event
	 * @return event of specified type for specified CTab item
	 */
	public Event createEventForCTabItem(final CTabItem swtCTabItem,
			final int type) {
		Event event = new Event();
		event.type = type;
		event.display = Display.getDisplay();
		event.time = (int) System.currentTimeMillis();
		event.item = swtCTabItem;
		event.widget = getCTabFolder(swtCTabItem);
		return event;
	}

	/**
	 * Notifies CTabFolder, containing specified {@link CTabItem},
	 * listeners about specified event. Field for type of the event in 
	 * specified event has to be properly set.
	 * 
	 * @param swtCTabItem CTab item to handle
	 * @param event event to notify
	 */
	public void notifyCTabFolder(final CTabItem swtCTabItem, final Event event) {
		Display.syncExec(new Runnable() {
			public void run() {
				swtCTabItem.getParent().notifyListeners(event.type, event);
			}
		});
	}

	/**
	 * Clicks close button on specified {@link CTabItem}.
	 * 
	 * @param swtCTabItem CTab item to handle
	 */
	public void clickCloseButton(final CTabItem swtCTabItem) {
		Rectangle rectangleCloseBox = Display
				.syncExec(new ResultRunnable<Rectangle>() {
					public Rectangle run() {
						try {
							Field field = org.eclipse.swt.custom.CTabItem.class
									.getDeclaredField("closeRect");
							field.setAccessible(true);
							return (Rectangle) field.get(swtCTabItem);
						} catch (Exception e) {

						}
						return null;
					}
				});
		logger.debug("Click on close button");
		int x = rectangleCloseBox.x + (rectangleCloseBox.width / 2);
		int y = rectangleCloseBox.y + (rectangleCloseBox.height / 2);
		notifyCTabFolder(
				swtCTabItem,
				createMouseEvent(swtCTabItem, SWT.MouseDown, x, y, 1, SWT.NONE,
						1));
		// this event being button 1 is what allows CTabItem to close
		notifyCTabFolder(
				swtCTabItem,
				createMouseEvent(swtCTabItem, SWT.MouseUp, x, y, 1,
						SWT.BUTTON1, 1));
	}

	/**
	 * Creates a mouse event for specified {@link CTabItem}. 
	 *
	 * @param swtCTabItem CTab item to handle
	 * @param type see {@link Event#type}
	 * @param x see {@link Event#x}
	 * @param y see {@link Event#y}
	 * @param button see {@link Event#button}
	 * @param stateMask see {@link Event#stateMask}
	 * @param count see {@link Event#count}
	 * @return mouse event for specified CTab item 
	 */
	private Event createMouseEvent(final CTabItem swtCTabItem, int type, int x,
			int y, int button, int stateMask, int count) {
		Event event = createEventForCTabItem(swtCTabItem, type);
		event.time = (int) System.currentTimeMillis();
		event.x = x;
		event.y = y;
		event.button = button;
		event.stateMask = stateMask;
		event.count = count;
		return event;
	}

	/**
	 * Finds out whether the close button of specified {@link CTabItem} is shown or not.
	 * 
	 * @param swtCTabItem CTab item to handle
	 * @return true if the close button is shown, false otherwise 
	 */
	public boolean isShowClose(final CTabItem swtCTabItem) {
		return Display.syncExec(new ResultRunnable<Boolean>() {
			public Boolean run() {
				return swtCTabItem.getShowClose();
			}
		});
	}
	
	/**
	 * Returns true if the tab is visible.
	 *
	 * @param swtCTabItem CTab item to handle
	 * @return true if the tab is visible
	 */
	public boolean isShowing(final CTabItem swtCTabItem) {
		return Display.syncExec(new ResultRunnable<Boolean>() {
			public Boolean run() {
				return swtCTabItem.isShowing();
			}
		});
	}

	/**
	 * Selects specified {@link CTabItem}.
	 * 
	 * @param cTabItem CTab item to handle
	 */
	public void select(final CTabItem cTabItem) {
		Display.syncExec(new Runnable() {
			public void run() {
				cTabItem.getParent().setSelection(cTabItem);
			}
		});
	}
	
	/**
	 * Activates specified ({@link CTabItem}.
	 *
	 * @param item CTabItem to handle
	 */
	public void activate(CTabItem item){
		select(item);
		notifyCTabFolder(item, createEventForCTabItem(item,SWT.Selection));
	}

	/**
	 * Sets focus on specified {@link CTabItem}.
	 * 
	 * @param ctabItem CTab item to handle
	 */
	public void setFocus(CTabItem ctabItem) {
		ControlHandler.getInstance().setFocus((getParent(ctabItem)));
	}
	
	/**
	 * Gets parent of ctabitem
	 * @param ctabItem to get parent of
	 * @return instance of CTabFolder which is parent of ctabitem
	 */
	public CTabFolder getParent(final CTabItem ctabItem){
		return Display.syncExec(new ResultRunnable<CTabFolder>() {

			@Override
			public CTabFolder run() {
				return ctabItem.getParent();
			}
		});
	}
	
	/**
	 * Gets tooltip text of ctabitem
	 * @param tabItem to get tooltip text from
	 * @return tooltip text of specified ctabitem
	 */
	public String getToolTipText(final CTabItem tabItem){
		return Display.syncExec(new ResultRunnable<String>() {

			@Override
			public String run() {
				return tabItem.getToolTipText();
			}
		});
	}
}
