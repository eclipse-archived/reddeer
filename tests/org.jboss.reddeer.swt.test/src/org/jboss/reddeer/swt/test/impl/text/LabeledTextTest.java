package org.jboss.reddeer.swt.test.impl.text;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.impl.text.DefaultText;
import org.jboss.reddeer.swt.impl.text.LabeledText;
import org.jboss.reddeer.swt.test.RedDeerTest;
import org.jboss.reddeer.swt.util.Display;
import org.junit.After;
import org.junit.Test;

public class LabeledTextTest extends RedDeerTest{
	
	@Override
	public void setUp() {
		super.setUp();
		Display.syncExec(new Runnable() {

			@Override
			public void run() {
				Shell shell = new Shell(org.eclipse.swt.widgets.Display.getDefault());
				shell.setText("Testing shell");
				createControls(shell);
				shell.open();
				shell.setFocus();
			}
		});
	}
	
	private void createControls(Shell shell){
		Label swtLabel= new Label(shell, SWT.BORDER);
		swtLabel.setText("Test label");
		swtLabel.setSize(100,30);
		swtLabel.setLocation(100, 50);
		Label swtLabelIcon= new Label(shell, SWT.BORDER);
		swtLabelIcon.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK));
		swtLabelIcon.setSize(100,30);
		swtLabelIcon.setLocation(100, 100);
		Text swtText = new Text(shell, SWT.LEFT);
		swtText.setText("Test text");
		swtText.setSize(100,30);
		swtText.setLocation(100,150);
		
		final Text swtTextStatus= new Text(shell, SWT.BORDER);
		swtTextStatus.setText("Status");
		swtTextStatus.setSize(100,30);
		swtTextStatus.setLocation(100, 200);

		swtText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				swtTextStatus.setText("focusLost");
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				swtTextStatus.setText("focusGained");
			}
		});
		
		Label swtLabel1= new Label(shell, SWT.BORDER);
		swtLabel1.setText("Test label1");
		swtLabel1.setSize(100,30);
		swtLabel1.setLocation(250, 50);
		Label swtLabelIcon1= new Label(shell, SWT.BORDER);
		swtLabelIcon1.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK));
		swtLabelIcon1.setSize(100,30);
		swtLabelIcon1.setLocation(250, 100);
		Text swtText1 = new Text(shell, SWT.LEFT);
		swtText1.setText("Test text1");
		swtText1.setSize(100,30);
		swtText1.setLocation(250,150);
		
		Label swtLabel2= new Label(shell, SWT.BORDER);
		swtLabel2.setText("Test label2");
		swtLabel2.setSize(100,30);
		swtLabel2.setLocation(400, 50);
		Label swtLabelIcon2= new Label(shell, SWT.BORDER);
		swtLabelIcon2.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK));
		swtLabelIcon2.setSize(100,30);
		swtLabelIcon2.setLocation(400, 100);
		Label swtLabelIcon3= new Label(shell, SWT.BORDER);
		swtLabelIcon3.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK));
		swtLabelIcon3.setSize(100,30);
		swtLabelIcon3.setLocation(400, 150);
		Text swtText2 = new Text(shell, SWT.LEFT);
		swtText2.setText("Test text2");
		swtText2.setSize(100,30);
		swtText2.setLocation(400,200);
	}
	
	@After
	public void cleanup() {
		Display.syncExec(new Runnable() {

			@Override
			public void run() {
				for (Shell shell : org.jboss.reddeer.swt.
						util.Display.getDisplay().getShells()) {
					if (shell.getText().equals("Testing shell")) {
						shell.dispose();
						break;
					}
				}
			}
		});
	}
	
	@Test
	public void findLabeledTextWithIcon(){
		new DefaultShell("Testing shell");
		assertTrue(new LabeledText("Test label").getText().equals("Test text"));
	}
	
	@Test
	public void findLabeledTextWithTwoIcons(){
		new DefaultShell("Testing shell");
		assertTrue(new LabeledText("Test label2").getText().equals("Test text2"));
	}
	
	@Test
	public void findLabeledTextWithoutIcon(){
		new DefaultShell("Testing shell");
		assertTrue(new LabeledText("Test label1").getText().equals("Test text1"));
	}
	
	@Test
	public void setFocusTest() {
		new DefaultShell("Testing shell");
		new LabeledText("Test label").setFocus();
		assertEquals("focusGained", new DefaultText(1).getText());
		new LabeledText("Test label1").setFocus();
		assertEquals("focusLost", new DefaultText(1).getText());
		new LabeledText("Test label").setFocus();
		assertEquals("focusGained", new DefaultText(1).getText());
	}

}
