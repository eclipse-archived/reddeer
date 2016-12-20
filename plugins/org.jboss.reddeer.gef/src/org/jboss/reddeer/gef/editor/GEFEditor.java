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
package org.jboss.reddeer.gef.editor;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.swt.widgets.Control;
import org.jboss.reddeer.common.util.Display;
import org.jboss.reddeer.common.wait.WaitUntil;
import org.jboss.reddeer.core.reference.ReferencedComposite;
import org.jboss.reddeer.gef.GEFLayerException;
import org.jboss.reddeer.gef.api.Palette;
import org.jboss.reddeer.gef.condition.EditorHasEditParts;
import org.jboss.reddeer.gef.handler.ViewerHandler;
import org.jboss.reddeer.gef.impl.editpart.internal.BasicEditPart;
import org.jboss.reddeer.gef.lookup.ViewerLookup;
import org.jboss.reddeer.gef.view.PaletteView;
import org.jboss.reddeer.workbench.impl.editor.DefaultEditor;

/**
 * 
 * GEF Editor
 * 
 * @author apodhrad
 * 
 */
public class GEFEditor extends DefaultEditor implements ReferencedComposite {

	protected GraphicalViewer viewer;

	/**
	 * Constructs a GEF editor with currently active editor. It also initializes graphical viewer which can be
	 * overridden by initGraphicalViewer().
	 */
	public GEFEditor() {
		super();
		initGraphicalViewer();
	}

	/**
	 * Constructs a GEF editor with a given title. It also initializes graphical viewer which can be overridden by
	 * initGraphicalViewer().
	 * 
	 * @param title
	 *            Editor title
	 */
	public GEFEditor(String title) {
		super(title);
		initGraphicalViewer();
	}

	/**
	 * Initializes a graphical viewer which is needed for GEF operations.
	 */
	protected void initGraphicalViewer() {
		viewer = ViewerLookup.getInstance().findGraphicalViewer(getEditorPart());
	}

	/**
	 * Gets the graphical viewer.
	 *
	 * @return the graphical viewer
	 */
	protected GraphicalViewer getGraphicalViewer() {
		if (viewer == null) {
			throw new GEFLayerException(
					"No graphical viewer has been initialized. Graphical viewer is needed for many GED operations.");
		}
		return viewer;
	}

	/**
	 * Returns the number of all available edit parts.
	 * 
	 * @return Number of edit parts
	 */
	public int getNumberOfEditParts() {
		return ViewerHandler.getInstance().getEditParts(viewer).size();
	}

	/**
	 * Clicks at the specified coordinates.
	 * 
	 * @param x
	 *            X-axis
	 * @param y
	 *            Y-axis
	 */
	public void click(int x, int y) {
		ViewerHandler.getInstance().click(viewer, x, y);
	}

	/**
	 * Returns a palette.
	 * 
	 * @return Palette
	 */
	public Palette getPalette() {
		new PaletteView().open();
		return ViewerHandler.getInstance().getPalette(viewer);
	}

	/**
	 * Adds a tool from a palette to the specified coordinates.
	 *
	 * @param tool            Tool label
	 * @param x            X-axis
	 * @param y            Y-axis
	 * @return the org.jboss.reddeer.gef.api. edit part
	 */
	public org.jboss.reddeer.gef.api.EditPart addToolFromPalette(String tool, final int x, final int y) {
		return addToolFromPalette(tool, null, x, y);
	}

	/**
	 * Adds a tool in a given group from a palette to the specified coordinates.
	 *
	 * @param tool            Tool label
	 * @param group            Group label
	 * @param x            X-axis
	 * @param y            Y-axis
	 * @return the org.jboss.reddeer.gef.api. edit part
	 */
	public org.jboss.reddeer.gef.api.EditPart addToolFromPalette(String tool, String group, final int x, final int y) {
		int oldCount = getNumberOfEditParts();

		final ViewerListener viewerListener = new ViewerListener();
		Display.asyncExec(new Runnable() {
			@Override
			public void run() {
				List<EditPart> editParts = ViewerHandler.getInstance().getEditParts(viewer);
				for (EditPart editPart : editParts) {
					editPart.addEditPartListener(viewerListener);
				}
			}
		});

		getPalette().activateTool(tool, group);
		click(x, y);

		return getAddedEditPart(viewerListener, oldCount);
	}

	/**
	 * Waits and detects the new component. Override this method if you need to change the default behavior, e.g. if
	 * there is a wizard before adding the component.
	 * 
	 * @param viewerListener
	 *            Viewer listener
	 * @param oldCount
	 *            Number of components before adding the new one
	 * @return The new component
	 */
	protected org.jboss.reddeer.gef.api.EditPart getAddedEditPart(ViewerListener viewerListener, int oldCount) {
		new WaitUntil(new EditorHasEditParts(this, oldCount));

		if (viewerListener.getAddedEditPart() == null) {
			throw new GEFLayerException("No new edit part was detected");
		}

		return new BasicEditPart(viewerListener.getAddedEditPart());
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.core.reference.ReferencedComposite#getControl()
	 */
	@Override
	public Control getControl() {
		return viewer.getControl();
	}

	/**
	 * Helper class for detecting changes in the viewer.
	 * 
	 * @author Andrej Podhradsky (andrej.podhradsky@gmail.com)
	 *
	 */
	private class ViewerListener implements EditPartListener {

		private EditPart addedEditPart;

		public EditPart getAddedEditPart() {
			return addedEditPart;
		}

		@Override
		public void childAdded(EditPart child, int index) {
			log.info("New edit part " + child);
			addedEditPart = child;
		}

		@Override
		public void partActivated(EditPart editpart) {

		}

		@Override
		public void partDeactivated(EditPart editpart) {

		}

		@Override
		public void removingChild(EditPart child, int index) {

		}

		@Override
		public void selectedStateChanged(EditPart editpart) {

		}

	}
}
