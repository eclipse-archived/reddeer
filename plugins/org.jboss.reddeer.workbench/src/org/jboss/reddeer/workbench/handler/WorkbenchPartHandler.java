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
package org.jboss.reddeer.workbench.handler;

import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.common.util.Display;
import org.jboss.reddeer.common.util.ResultRunnable;
import org.jboss.reddeer.core.handler.CTabItemHandler;
import org.jboss.reddeer.core.lookup.WidgetLookup;
import org.jboss.reddeer.workbench.exception.WorkbenchLayerException;

/**
 * WorkbenchPartHandler handles operations common for both editor and view instances.
 * 
 * @author rawagner
 *
 */
public class WorkbenchPartHandler {
	
	protected final Logger log = Logger.getLogger(this.getClass());
	private static WorkbenchPartHandler instance;
	
	/**
	 * Gets instance of WorkbenchPartHandler.
	 * 
	 * @return instance of WorkbenchPartHandler
	 */
	public static WorkbenchPartHandler getInstance(){
		if(instance == null){
			instance = new WorkbenchPartHandler();
		}
		return instance;
	}
	
	/**
	 * Gets title of specified {@link IWorkbenchPart}.
	 *
	 * @param workbenchPart the workbench part
	 * @return title of specified workbench part
	 */
	public String getTitle(final IWorkbenchPart workbenchPart) {
		return Display.syncExec(new ResultRunnable<String>() {

			@Override
			public String run() {
				return workbenchPart.getTitle();
			}
		});
	}
	
	/**
	 * Gets title tool tip of specified {@link IWorkbenchPart}.
	 *
	 * @param workbenchPart the workbench part
	 * @return title tool tip text of specified workbench part
	 */
	public String getTitleToolTip(final IWorkbenchPart workbenchPart) {
		return Display.syncExec(new ResultRunnable<String>() {

			@Override
			public String run() {
				return workbenchPart.getTitleToolTip();
			}
		});
	}
	
	/**
	 * Perform action created from specified {@link ActionFactory}.
	 * 
	 * @param actionFactory action factory to create action to perform
	 */
	public void performAction(final ActionFactory actionFactory){
		Display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				IWorkbenchAction action= actionFactory.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
				action.run();
			}
		});
	}
	
	/**
	 * Activates workbench part containing specified widget.
	 * 
	 * @param widget widget of workbench part to activate
	 */
	public void activateWorkbenchPartWithWidget(Widget widget) {
		
		final WorkbenchPartWidgets workbenchPartWidgets = getWorkbenchPartWidgetsForWidget(widget);
		
		if (workbenchPartWidgets != null){
			Display.syncExec(new Runnable() {			
				@Override
				public void run() {
					CTabItem[] cTabItems = workbenchPartWidgets.workbenchPartCTabFolder.getItems();
					int index = 0;
					boolean cTabItemFound = false;
					while (!cTabItemFound && index < cTabItems.length){
						if (cTabItems[index].getControl() == workbenchPartWidgets.firstCTabItemControl){
							cTabItemFound = true;
							log.debug("Activating Workbench part with label: '" + cTabItems[index].getText() + "'");
							CTabItemHandler.getInstance().activate(cTabItems[index]);
						}		
						else{
							index++;
						}
					}
				}
			});
		}
		else{
			throw new WorkbenchLayerException("Unable to activate workbench part with widget. No cTabFolder found in widget path");
		}
	}
	
	/**
	 * Gets title of Workbench part containing specified widget.
	 *
	 * @param widget widget of workbench part to get title of
	 * @return the title of workbench part with widget
	 */
	public String getTitleOfWorkbenchPartWithWidget(Widget widget) {
		
		final WorkbenchPartWidgets workbenchPartWidgets = getWorkbenchPartWidgetsForWidget(widget);
		
		if (workbenchPartWidgets != null){
			return Display.syncExec(new ResultRunnable<String>() {
				@Override
				public String run() {
					CTabItem[] cTabItems = workbenchPartWidgets.workbenchPartCTabFolder.getItems();
					int index = 0;
					String cTabItemTitle = null;
					while (cTabItemTitle == null && index < cTabItems.length){
						if (cTabItems[index].getControl() == workbenchPartWidgets.firstCTabItemControl){
							cTabItemTitle = cTabItems[index].getText();
						}		
						else{
							index++;
						}
					}
					return cTabItemTitle;
				}
			});
		}
		else{
			throw new WorkbenchLayerException("Unable to get title of workbench part with widget. No cTabFolder found in widget path");
		}
	}
	
	/**
	 * Stores information about top workbench part widgets.
	 * 
	 * @author vlado pakan
	 */
	private class WorkbenchPartWidgets {
		
		public WorkbenchPartWidgets(CTabFolder workbenchPartCTabFolder,
				Control firstCTabItemControl) {
			super();
			this.workbenchPartCTabFolder = workbenchPartCTabFolder;
			this.firstCTabItemControl = firstCTabItemControl;
		}
		
		public CTabFolder workbenchPartCTabFolder = null;
		public Control firstCTabItemControl = null;

	}
	
	/**
	 * Gets CTabFolder and CTabItem containing specified widget.
	 * 
	 * @param widget widget contained within returned {@link WorkbenchPartWidgets}
	 * @return {@link WorkbenchPartWidgets}
	 */
	private WorkbenchPartWidgets getWorkbenchPartWidgetsForWidget(Widget widget){
		
		WorkbenchPartWidgets workbenchPartWidgets = null;
		
		List<org.eclipse.swt.widgets.Widget> pathToWidget = WidgetLookup.getInstance().getPathToWidget(widget);
		Iterator<org.eclipse.swt.widgets.Widget> itWidget = pathToWidget.iterator();
		boolean foundCTabFolder = false;
		org.eclipse.swt.widgets.Widget currentWidget = null;
		while (!foundCTabFolder && itWidget.hasNext()){
			currentWidget = itWidget.next();
			if (currentWidget instanceof org.eclipse.swt.custom.CTabFolder){
				foundCTabFolder = true;
			}
		}
		
		if (foundCTabFolder){
			workbenchPartWidgets = new WorkbenchPartWidgets((CTabFolder)currentWidget,
				(Control)itWidget.next());
		}
		
		return workbenchPartWidgets;
	}
	
}