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
package org.eclipse.reddeer.direct.project;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

/**
 * Utils handling project via API
 * @author vlado pakan
 *
 */
public class Project {
	
	/**
	 * Deletes Eclipse project via Eclipse API.
	 *
	 * @param projectName the project name
	 * @param deleteContent the delete content
	 * @param force the force
	 */
	public static void delete(String projectName, boolean deleteContent , boolean force ){
		try {
			getProject(projectName).delete(deleteContent, force, null);
		} catch (CoreException ce) {
			throw new RuntimeException("Unable to delete project " + projectName, ce);
		}
	}
	
	/**
	 * Gets project nature ids. Nature ids can be java, maven etc.
	 * 
	 * @param projectName name of project which ids to get
	 * @return list of project ids or null if project does not exist or there is no nature IDl
	 */
	public static List<String> getProjectNatureIds(final String projectName) {
		List<String> natureIds = null;
		
		try {
			natureIds = Arrays.asList(getProject(projectName).getDescription().getNatureIds());
		} catch (CoreException ex) {
			throw new RuntimeException("Cannot get natures of project " + projectName +". Project is not opened.");
		}
		return natureIds;
	}
	
	/**
	 * Checks if a project of given name exists.
	 *
	 * @param projectName name of the project to check
	 * @return true if project exists, false otherwise
	 */
	public static boolean isProject(String projectName) {
		return getProject(projectName).exists();

	}
	
	/**
	 * Checks if project with given name is open.
	 *
	 * @param projectName name of the project to check
	 * @return true if project is open, false otherwise
	 */
	public static boolean isOpen(String projectName) {
		return getProject(projectName).isOpen();
	}
	
	private static IProject getProject(String projectName){
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}
}
