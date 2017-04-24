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
package org.eclipse.reddeer.workbench.api;

import java.util.List;

import org.eclipse.reddeer.jface.text.contentassist.ContentAssistant;
import org.eclipse.reddeer.workbench.impl.editor.Marker;

/**
 * Interface with base operations which can be performed with editor.
 * @author rhopp
 * @author rawagner
 */
public interface Editor extends WorkbenchPart {
    /**
     * Returns editor title tooltip.
     * @return editor title tooltip
     */
    String getTitleToolTip();

    /**
     * Checks if editor is dirty.
     * @return true if editor is dirty
     */
    boolean isDirty();

    /**
     * Tries to perform save on this editor.
     */
    void save();

    /**
     * Closes this editor.
     * @param save If true, content will be saved
     */
    void close(boolean save);

    /**
     * Closes all editors.
     * @param save If true, content will be saved
     */
    void closeAll(boolean save); 

    /**
     * Checks if editor is active.
     * @return whether is this editor currently active and has focus.
     */
    boolean isActive();

    /**
     * Opens content assistant.
     * @return Content assistant shell
     */
    ContentAssistant openContentAssistant();

    /**
     * Opens quickfix content assistant.
     * @return Content assistant shell
     */
    ContentAssistant openQuickFixContentAssistant();

    /**
     * Opens open on assistant.
     * @return Content assistant shell
     */
    ContentAssistant openOpenOnAssistant();

    /**
     * Returns editor validation markers.
     * @return editor validation markers
     */
    List<Marker> getMarkers();
    
    /**
     * Gets content assistant opened automatically by instructions defined within run method
     * of execute parameter or null in case Content Assistant shell was not opened.
     *
     * @param execute the execute
     * @return Content assistant
     */
    ContentAssistant getAutoContentAssistant(Runnable execute);

    /**
     * Returns an editor file associated to the editor.
     * 
     * @return Editor file associated to the editor
     */
    EditorFile getAssociatedFile();  
}
