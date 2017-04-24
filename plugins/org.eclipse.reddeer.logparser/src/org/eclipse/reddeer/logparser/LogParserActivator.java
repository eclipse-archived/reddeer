/*******************************************************************************
 * Copyright (c) 2017 Red Hat, Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.reddeer.logparser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.reddeer.logparser.editors.LogParserEditor;
import org.eclipse.reddeer.logparser.views.LogParserView;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class LogParserActivator extends AbstractUIPlugin{

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.reddeer.logparser"; //$NON-NLS-1$

	// The shared instance
	private static LogParserActivator plugin;
	
	private static List<IPropertyChangeListener> propertyChangeListeners = new ArrayList<IPropertyChangeListener>();
	
	/**
	 * The constructor
	 */
	public LogParserActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		LogParserView.disposeImages();
		LogParserEditor.disposeImages();
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static LogParserActivator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	public static void addPropertyChangeListener (IPropertyChangeListener listener) {
		LogParserActivator.propertyChangeListeners.add(listener);
	}
	
	public static void removePropertyChangeListener (IPropertyChangeListener listener) {
		LogParserActivator.propertyChangeListeners.remove(listener);
	}
	
	public static void notifyAllPropertyPageListeners (PropertyChangeEvent event) {
		for (IPropertyChangeListener listener : LogParserActivator.propertyChangeListeners){
			listener.propertyChange(event);
		}
	}
	
}
