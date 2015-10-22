package org.jboss.reddeer.eclipse.wst.xml.ui.tabletree;

import org.eclipse.swt.widgets.TreeItem;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jboss.reddeer.core.handler.TreeItemHandler;
import org.jboss.reddeer.core.lookup.TreeItemLookup;
import org.jboss.reddeer.core.matcher.AndMatcher;
import org.jboss.reddeer.core.matcher.TreeItemTextMatcher;
import org.jboss.reddeer.core.util.Display;
import org.jboss.reddeer.core.util.ResultRunnable;
import org.jboss.reddeer.swt.impl.tree.DefaultTree;

/**
 * Represents the Design page of {@link XMLMultiPageEditor}. The page
 * allows to work with XML in tree format. 
 * @author Lucia Jelinkova
 *
 */
public class XMLDesignPage {
	
	/**
	 * Return node specified by the path (from the top of the tree)
	 * @param path
	 * @return
	 */
	public XMLDesignPageNode getNode(String... path){
		TreeItem item = TreeItemLookup.getInstance().getTreeItem(new DefaultTree().getSWTWidget(), 0, createNodeMatchers(path));
		return new XMLDesignPageNode(item);
	}

	/**
	 * Return node specified by the path (from the specified node)
	 * @param node
	 * @param path
	 * @return
	 */
	public XMLDesignPageNode getNode(XMLDesignPageNode node, String... path){
		TreeItem item = TreeItemLookup.getInstance().getTreeItem(node.item, 0, createNodeMatchers(path));
		return new XMLDesignPageNode(item);
	}
	
	/**
	 * Return the value of the specified attribute of the specified node. 
	 * @param node
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAttributeValue(XMLDesignPageNode node, String name){
		TreeItem item = TreeItemLookup.getInstance().getTreeItem(node.item, 0, createAttributeMatcher(name));
		return TreeItemHandler.getInstance().getText(item, 1);
	}
	
	/**
	 * Return text content of the specified node
	 * @param node
	 * @return
	 */
	public String getContent(XMLDesignPageNode node){
		return node.getTreeItemText(1);
	}
	
	/**
	 * Represents one XML node on design page
	 * 
	 * @author Lucia Jelinkova
	 *
	 */
	public static class XMLDesignPageNode {
		
		private TreeItem item;
		
		public XMLDesignPageNode(TreeItem item) {
			this.item = item;
		}
		
		public String getName(){
			return getTreeItemText(0);
		}
		
		private String getTreeItemText(int cellIndex){
			return TreeItemHandler.getInstance().getText(item, cellIndex);
		}
	}
	
	@SuppressWarnings("unchecked")
	private Matcher<org.eclipse.swt.widgets.TreeItem>[] createNodeMatchers(String[] treeItemPath) {
		Matcher<org.eclipse.swt.widgets.TreeItem>[] matchers = new Matcher[treeItemPath.length];
		for (int i = 0; i < treeItemPath.length; i++){
			matchers[i] = new AndMatcher(new NodeMatcher(), new TreeItemTextMatcher(treeItemPath[i]));
		}
		return matchers;
	}
	
	@SuppressWarnings("unchecked")
	private Matcher<TreeItem> createAttributeMatcher(String name) {
		return new AndMatcher(new AttributeMatcher(), new TreeItemTextMatcher(name));
	}
	
	private static class NodeMatcher extends TypeSafeMatcher<org.eclipse.swt.widgets.TreeItem> {

		@Override
		public void describeTo(Description description) {
			description.appendText("represents XML node");
		}

		@Override
		protected boolean matchesSafely(final org.eclipse.swt.widgets.TreeItem item) {
			String dataClassName = Display.syncExec(new ResultRunnable<String>() {
				
				@Override
				public String run() {
					return item.getData().getClass().getSimpleName();
				}
			});
			return dataClassName.equals("ElementImpl");
		}
	}
	
	private static class AttributeMatcher extends TypeSafeMatcher<org.eclipse.swt.widgets.TreeItem> {

		@Override
		public void describeTo(Description description) {
			description.appendText("represents XML attribute");
		}

		@Override
		protected boolean matchesSafely(final org.eclipse.swt.widgets.TreeItem item) {
			String dataClassName = Display.syncExec(new ResultRunnable<String>() {
				
				@Override
				public String run() {
					return item.getData().getClass().getSimpleName();
				}
			});
			return dataClassName.equals("AttrImpl");
		}
	}
}
