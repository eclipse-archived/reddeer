package org.jboss.reddeer.eclipse.jdt.ui.packageexplorer;

import java.util.ArrayList;
import java.util.List;

import org.jboss.reddeer.junit.logging.Logger;
import org.jboss.reddeer.swt.api.TreeItem;
import org.jboss.reddeer.swt.condition.JobIsRunning;
import org.jboss.reddeer.swt.condition.ShellWithTextIsActive;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.menu.ContextMenu;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.wait.TimePeriod;
import org.jboss.reddeer.swt.wait.WaitWhile;

/**
 * Represents a project item of {@link Project}.
 * 
 * @author Vlado Pakan
 * 
 */
public class ProjectItem {
	protected final Logger log = Logger.getLogger(ProjectItem.class);
	
	protected TreeItem treeItem;
	private Project project;
	private String[] path;
	

	public ProjectItem(TreeItem treeItem, Project project, String... path) {
		this.treeItem = treeItem;
		this.path = path;
		this.project = project;
	}

	public void open() {
		select();
		treeItem.doubleClick();
	}

	public void delete() {
		select();
        log.debug("Delete project item " + treeItem.getText() + " via Package Explorer");
	    new ContextMenu("Delete").select();
	    new DefaultShell("Delete");
		new PushButton("OK").click();
		new WaitWhile(new ShellWithTextIsActive("Delete"),TimePeriod.LONG);
		new WaitWhile(new JobIsRunning(), TimePeriod.LONG);
	}

	public void select() {
		TreeItem tmpItem = project.getTreeItem();
		
		// go through whole hierarchy instead of 1 level
		for (int i = 0; i < path.length - 1; i++) {
			String partialPath = path[i];
			for (TreeItem item : tmpItem.getItems()) {
				if (parseItemName(item).equals(partialPath)) {
					item.expand();
					tmpItem = item;
					break;
				}
			}
		}

		// finally, find and select item
		for (TreeItem item : tmpItem.getItems()) {
			if (parseItemName(item).equals(path[path.length - 1])) {
				item.select();
				break;
			}
		}
	}
	
	private String parseItemName(TreeItem item) {
		if (item.getText().charAt(0) == '>') {
			return item.getText().split(" ")[1];
		}
		return item.getText();
	}
	
	public boolean isSelected() {
		return treeItem.isSelected();
	}
	
	public ProjectItem getChild(String text) {
		String[] childPath = new String[path.length + 1];
		System.arraycopy(path, 0, childPath, 0, path.length);
		childPath[childPath.length - 1] = text;
		return new ProjectItem(treeItem.getItem(text), project, childPath);
	}
	
	public String getText() {
		return treeItem.getText();
	}
	
	public Project getProject() {
		return project;
	}
	
	public List<ProjectItem> getChildren() {
		List<ProjectItem> childrens = new ArrayList<ProjectItem>();
		for(TreeItem ti : treeItem.getItems()) {
			childrens.add(getChild(ti.getText()));
		}
		return childrens;
	}
}
