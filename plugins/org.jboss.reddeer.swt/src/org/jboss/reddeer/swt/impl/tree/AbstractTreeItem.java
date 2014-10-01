package org.jboss.reddeer.swt.impl.tree;

import java.util.List;

import org.eclipse.swt.SWT;
import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.swt.api.Tree;
import org.jboss.reddeer.swt.api.TreeItem;
import org.jboss.reddeer.swt.condition.TreeItemHasMinChildren;
import org.jboss.reddeer.swt.handler.TreeHandler;
import org.jboss.reddeer.swt.handler.TreeItemHandler;
import org.jboss.reddeer.swt.handler.WidgetHandler;
import org.jboss.reddeer.swt.util.Display;
import org.jboss.reddeer.swt.wait.TimePeriod;
import org.jboss.reddeer.swt.wait.WaitUntil;
import org.jboss.reddeer.swt.widgets.AbstractWidget;

/**
 * Basic TreeItem class is abstract class for all Tree Item implementations
 * 
 * @author jjankovi, mlabuda@redhat.com
 * 
 */
public abstract class AbstractTreeItem extends AbstractWidget<org.eclipse.swt.widgets.TreeItem> implements TreeItem {

	private static final Logger logger = Logger.getLogger(AbstractTreeItem.class);

	private TreeHandler treeHandler = TreeHandler.getInstance();
	
	protected AbstractTreeItem(org.eclipse.swt.widgets.TreeItem swtWidget) {
		super(swtWidget);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public void select() {
		logger.info("Select tree item " + getText());
		treeHandler.select(swtWidget);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public String getText() {
		return WidgetHandler.getInstance().getText(swtWidget);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public String getToolTipText() {
		return TreeItemHandler.getInstance().getToolTipText(swtWidget);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public String getCell(final int index) {
		return TreeItemHandler.getInstance().getText(swtWidget, index);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public String[] getPath() {
		return treeHandler.getPath(swtWidget);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public void expand() {
		expand(TimePeriod.SHORT);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public void expand(TimePeriod timePeriod) {
		logger.info("Expand tree item " + getText() + " and wait with time period " 
			+ timePeriod.getSeconds());
		treeHandler.expand(swtWidget, timePeriod);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public void collapse() {
		logger.info("Collapse tree item " + getText());
		treeHandler.collapse(swtWidget);
	}

	/**
	 * Return direct descendant specified with parameters
	 * 
	 * @param text
	 *            text of tree item which should be returned
	 * @return direct descendant specified with parameters
	 */
	public TreeItem getItem(final String text) {
		return treeHandler.getItem(swtWidget, text);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public void doubleClick() {
		logger.info("Double click tree item " + getText());
		select();
		logger.debug("Notify tree about mouse double click event");
		treeHandler.notifyTree(getSWTWidget(), treeHandler.createEventForTree(
				getSWTWidget(), SWT.MouseDoubleClick));
		logger.debug("Notify tree about default selection event");
		treeHandler.notifyTree(getSWTWidget(), treeHandler.createEventForTree(
				getSWTWidget(), SWT.DefaultSelection));
		
		Display.syncExec(new Runnable() {
			@Override
			public void run() {
				// flush events
			}
		});
		
		logger.debug("Double click successfull");
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public boolean isSelected() {
		return treeHandler.isSelected(swtWidget);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public boolean isDisposed() {
		return swtWidget.isDisposed();
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public void setChecked(final boolean check) {
		logger.info("Check tree item " + getText());
		treeHandler.setChecked(swtWidget, check);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public boolean isChecked() {
		return treeHandler.isChecked(swtWidget);
	}

	/**
	 * Return swt widget of Tree Item
	 */
	public org.eclipse.swt.widgets.TreeItem getSWTWidget() {
		return swtWidget;
	}

	/**
	 * Returns children tree items
	 * 
	 * @return
	 */
	@Override
	public List<TreeItem> getItems() {
		expand(TimePeriod.SHORT);
		return treeHandler.getChildrenItems(swtWidget);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public Tree getParent() {
		return treeHandler.getParent(swtWidget);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public boolean isExpanded() {
		return treeHandler.isExpanded(swtWidget);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public void expand(int minItemsCount) {
		expand(minItemsCount, TimePeriod.SHORT);
	}

	/**
	 * See {@link TreeItem}
	 */
	@Override
	public void expand(int minItemsCount, TimePeriod timePeriod) {
		logger.info("Expand tree item " + getText() + 
			" and wait for at least " + minItemsCount + " items");
		expand();
		new WaitUntil(new TreeItemHasMinChildren(this, minItemsCount),
				timePeriod);
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("TreeItem: ");
		boolean isFirst = true;
		for (String pathItem : this.getPath()) {
			if (isFirst) {
				isFirst = false;
			} else {
				result.append(" > ");
			}
			result.append(pathItem);

		}
		return result.toString();
	}

	@Override
	public void setText(String text, int index) {
		logger.info("Set text to tree item at index " + index + ": " + text);
		TreeItemHandler.getInstance().setText(swtWidget, index, text);
	}

	@Override
	public void setText(String text) {
		logger.info("Set text to tree item: " + text);
		TreeItemHandler.getInstance().setText(swtWidget, 0, text);
	}
	
	class TreeItemTexts {
		private String nonStyledText;
		private String[] styledTexts;
		
		public TreeItemTexts(String nonStyledText, String[] styledTexts) {
			this.nonStyledText = nonStyledText;
			this.styledTexts = styledTexts;
		}
		
		public String getNonStyledText() {
			return nonStyledText;
		}
		
		public String[] getStyledTexts() {
			return styledTexts;
		}
	}
}