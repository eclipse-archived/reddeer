package org.jboss.reddeer.eclipse.jdt.ui.packageexplorer;

import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.eclipse.jface.exception.JFaceLayerException;
import org.jboss.reddeer.eclipse.jface.viewer.handler.TreeViewerHandler;
import org.jboss.reddeer.eclipse.utils.DeleteUtils;
import org.jboss.reddeer.swt.api.TreeItem;
import org.jboss.reddeer.swt.condition.JobIsRunning;
import org.jboss.reddeer.swt.impl.button.CheckBox;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.menu.ContextMenu;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.wait.TimePeriod;
import org.jboss.reddeer.swt.wait.WaitWhile;

/**
 * Represents a project on {@link PackageExplorer}.
 * 
 * @author Vlado Pakan
 * 
 */
public class Project {
	protected final Logger log = Logger.getLogger(Project.class);

	private TreeItem treeItem;
	
	private TreeViewerHandler treeViewerHandler = TreeViewerHandler.getInstance();

	/**
	 * Creates a project represented by specified {@link TreeItem}.
	 * 
	 * @param treeItem encapsulated tree item
	 */
	public Project(TreeItem treeItem) {
		this.treeItem = treeItem;
	}

	/**
	 * Deletes project from Project Explorer.
	 * 
	 * @param deleteFromFileSystem whether project should be deleted from file 
	 * system or not
	 */
	public void delete(boolean deleteFromFileSystem) {
		select();
		log.debug("Delete project " + treeViewerHandler.getNonStyledText(treeItem) + " via Explorer");new ContextMenu("Refresh").select();
		new ContextMenu("Refresh").select();
		new WaitWhile(new JobIsRunning(), TimePeriod.VERY_LONG);
		new ContextMenu("Delete").select();
		new DefaultShell("Delete Resources");
		new CheckBox().toggle(deleteFromFileSystem);
		DefaultShell shell = new DefaultShell();
		String deleteShellText = shell.getText();
		new PushButton("OK").click();
		DeleteUtils.handleDeletion(deleteShellText);
	}

	/**
	 * Selects the project.
	 */
	public void select() {
		treeItem.select();
	}

	/**
	 * Gets name of the project without decorators.
	 * 
	 * @return name of the project without decorators
	 */
	public String getName() {
		return treeViewerHandler.getNonStyledText(treeItem);
	}

	/**
	 * Gets decorated parts of project labels. Such parts could be for
	 * example remote and branch on a git project or tracking decorator ">"
	 * before project name in a label.
	 * 
	 * @return String[] of decorated texts on a whole project label
	 */
	public String[] getDecoratedParts() {
		return treeViewerHandler.getStyledTexts(treeItem);
	}

	/**
	 * Gets encapsulated {@link TreeItem} representating the project.
	 * 
	 * @return encapsulated tree item
	 */
	public TreeItem getTreeItem() {
		return treeItem;
	}

	/**
	 * Finds out whether project contain item specified by the path or not.
	 * 
	 * @param path path to the tree item
	 * @return true if project contains tree item specified by the path, 
	 * 	false otherwise
	 */
	public boolean containsItem(String... path) {
		boolean result = false;
		try {
			getProjectItem(path);
			result = true;
		} catch (JFaceLayerException jfaceException) {
			result = false;
		}
		return result;
	}

	/**
	 * Gets {@link ProjectItem} specified by path without decorators.
	 * 
	 * @param path path to the tree item without decorators
	 * @return tree item specified by the path
	 */
	public ProjectItem getProjectItem(String... path) {
		return new ProjectItem(treeViewerHandler.getTreeItem(treeItem, path), this, path);
	}

	/**
	 * Finds out whether the project is selected or not.
	 * 
	 * @return true if project is selected, false otherwise
	 */
	public boolean isSelected() {
		return treeItem.isSelected();
	}

	/**
	 * Gets whole text of the project displayed in Project Explorer
	 * as it is (decorators are included).
	 * 
	 * @return whole label of the project
	 */
	public String getText() {
		return treeItem.getText();
	}
}
