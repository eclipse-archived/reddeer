package org.jboss.reddeer.swt.condition;


import org.jboss.reddeer.common.condition.WaitCondition;
import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.swt.api.Tree;

/**
 * Condition is fulfilled when tree has selected specified amount of items.
 *
 * @author Vlado Pakan
 *
 */
public class TreeHasSelectedItems implements WaitCondition {
	private Logger log = Logger.getLogger(TreeHasSelectedItems.class);
	private Tree tree;
	private int numSelectedItems;
	
	/**
	 * Construct tree has selected items condition. 
	 * @param tree given tree
	 */
	public TreeHasSelectedItems(Tree tree) {
		super();
		this.tree = tree;
		this.numSelectedItems = 1;
	}
	/**
	 * Construct tree has selected items condition. 
	 * @param tree given tree
	 */
	public TreeHasSelectedItems(Tree tree , int numSelectedItems) {
		super();
		this.numSelectedItems = numSelectedItems;
		this.tree = tree;
	}

	@Override
	public boolean test() {
		int count = tree.getSelectedItems().size();
		log.trace("Count of selected tree items:" + count);
		if (count == numSelectedItems) {
			log.trace(tree.getSelectedItems().get(0).getText());
			return true;
		}
		return false;
	}

	@Override
	public String description() {
		return "tree has " + numSelectedItems + " selected items";
	}
}
