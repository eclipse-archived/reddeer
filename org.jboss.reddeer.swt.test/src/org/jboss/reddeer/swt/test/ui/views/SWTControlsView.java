package org.jboss.reddeer.swt.test.ui.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class SWTControlsView extends ViewPart {

	private Label label;
	private Text text;
	
	@Override
	public void createPartControl(Composite composite) {
		RowLayout rl = new RowLayout();
		composite.setLayout(rl);
		
		label = new Label(composite, SWT.NONE);
		label.setText("Name:");
		text = new Text(composite, SWT.NONE);
		text.setText("Original text");
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}
	

}
