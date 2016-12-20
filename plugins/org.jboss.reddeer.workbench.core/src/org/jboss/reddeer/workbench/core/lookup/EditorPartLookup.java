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
package org.jboss.reddeer.workbench.core.lookup;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.hamcrest.Matcher;
import org.jboss.reddeer.common.condition.AbstractWaitCondition;
import org.jboss.reddeer.common.exception.WaitTimeoutExpiredException;
import org.jboss.reddeer.common.util.Display;
import org.jboss.reddeer.common.util.ResultRunnable;
import org.jboss.reddeer.common.wait.WaitUntil;
import org.jboss.reddeer.workbench.core.exception.WorkbenchCoreLayerException;
import org.jboss.reddeer.common.matcher.AndMatcher;

/**
 * Editor part lookup provides methods for finding editor parts. 
 * 
 * @author Lucia Jelinkova
 *
 */
@SuppressWarnings("unchecked")
public class EditorPartLookup {

	private static EditorPartLookup instance;

	private EditorPartLookup(){

	}

	/**
	 * Gets singleton instance of EditorPartLookup.
	 * 
	 * @return instance of EditorPartLookup
	 */
	public static EditorPartLookup getInstance(){
		if(instance == null){
			instance = new EditorPartLookup();
		}
		return instance;
	}
	
	/**
	 * Gets currently active or first inactive editor. If there is no opened editor,  
	 * {@link WorkbenchLayerException} is thrown
	 * . 
	 * @return active or first inactive editor. 
	 */
	public IEditorPart getEditor(){
		IEditorPart editorPart = getActiveEditor();
		if (editorPart == null) {
			final IEditorReference[] editorReferences = getAllEditors();
			if(editorReferences.length == 0){
				throw new WorkbenchCoreLayerException("There is no opened editor at the moment.");
			}
			editorPart = Display.syncExec(new ResultRunnable<IEditorPart>() {
					
				@Override
				public IEditorPart run() {
					return editorReferences[0].getEditor(false);
				}
			});
		}
		return editorPart;
	}

	/**
	 * Gets currently active editor or null. 
	 * 
	 * @return active editor 
	 */
	public IEditorPart getActiveEditor(){
		IWorkbenchPart workbenchPart =  WorkbenchPartLookup.getInstance().getActiveWorkbenchPart();
		IEditorPart editorPart = null;
		if (!(workbenchPart instanceof IEditorPart)) {
			editorPart = Display.syncExec(new ResultRunnable<IEditorPart>() {

				@Override
				public IEditorPart run() {
					return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
							.getActiveEditor();
				}
			});
		} else {
			editorPart = (IEditorPart)workbenchPart;
		}
		return editorPart;
	}
	
	/**
	 * Gets editor matching specified matchers. If there is no opened editor, {@link WorkbenchLayerException}
	 * is thrown.
	 * 
	 * @param matchers matchers to match editor
	 * @return editor matching matchers
	 */
	public IEditorPart getEditor(final Matcher<IEditorPart>... matchers) {
		EditorPartIsFound found = new EditorPartIsFound(matchers);
        try {
            new WaitUntil(found);
        } catch (WaitTimeoutExpiredException ex) {
            throw new WorkbenchCoreLayerException("Unable to find editor matching specified matchers");
        }
        return found.getPart();
	}
	
	private IEditorPart getEditorInternal(final Matcher<IEditorPart>... matchers) {
		final IEditorReference[] editorReferences = getAllEditors();
		
		return Display.syncExec(new ResultRunnable<IEditorPart>() {

			@Override
			public IEditorPart run() {
				AndMatcher andMatcher  = new AndMatcher(matchers);
				
				for (IEditorReference editorReference : editorReferences) {
					IEditorPart part = editorReference.getEditor(false);
					if (andMatcher.matches(part)) {
						return part;
					}
				}
				return null;
			}
		});
	}
	
	private IEditorReference[] getAllEditors(){
		return Display.syncExec(new ResultRunnable<IEditorReference[]>() {

			@Override
			public IEditorReference[] run() {
				IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				return activeWorkbenchWindow.getActivePage().getEditorReferences();
			}
		});
	}
	
    /**
     * Condition is met when editor part is found.
     * 
     * @author rawagner
     */
    private class EditorPartIsFound extends AbstractWaitCondition {

        private Matcher<IEditorPart>[] matchers;
        
        private IEditorPart part;

        public EditorPartIsFound(Matcher<IEditorPart>[] matchers) {
            this.matchers = matchers;
        }

        @Override
        public boolean test() {
            part = EditorPartLookup.getInstance().getEditorInternal(matchers);
            
            if (part == null) {
                return false;
            }
            
            return true;
        }

        @Override
        public String description() {
            return "editorPart matching " + matchers + " is found";
        }

        public IEditorPart getPart() {
            return part;
        }
    }
}
