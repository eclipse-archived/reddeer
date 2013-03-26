package org.jboss.reddeer.eclipse.jdt.ui.packageexplorer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jboss.reddeer.eclipse.exception.EclipseLayerException;
import org.jboss.reddeer.swt.api.TreeItem;
import org.jboss.reddeer.swt.impl.tree.DefaultTree;
import org.jboss.reddeer.workbench.view.impl.WorkbenchView;

/**
 * Represents Package Explorer in Eclipse
 * 
 * @author Vlado Pakan
 *
 */
public class PackageExplorer extends WorkbenchView {
    
	public PackageExplorer() {
		super("Package Explorer");
	}
	
	public Project selectProject (String projectName){
		Project project = getProject(projectName);
		project.select();
		return project;
	}
	
	public void selectProjects(String... projectName){
	  LinkedList<TreeItem> itemsToSelect = new LinkedList<TreeItem>();
		for(String pname: projectName){
		  itemsToSelect.add(getProject(pname).getTreeItem()); //check if project exists
		}
		new DefaultTree().selectItems(itemsToSelect.toArray(new TreeItem[]{}));
	}
	
	public boolean containsProject (String projectName){
	  boolean result = false;
	  try{
		getProject(projectName);;
	    result = true;
	  } catch (EclipseLayerException ele){
	    result = false;
	  }
	  return result;
	}
	
	public List<Project> getProjects(){
		List<Project> projects = new ArrayList<Project>();

		for (TreeItem item : getPackageExplorerTree().getItems()){
			projects.add(new Project(item));
		}
		return projects;
	}
	
	public DefaultTree getPackageExplorerTree(){
		open();
		return new DefaultTree();
	}
	
	public Project getProject(String projectName){
		for (Project project : getProjects()){
			if (project.getName().equals(projectName)){
				return project;
			}
		}
		throw new EclipseLayerException("There is no project with name " + projectName);
	}	

}
