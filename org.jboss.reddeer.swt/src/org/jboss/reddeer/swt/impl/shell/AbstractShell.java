package org.jboss.reddeer.swt.impl.shell;

import org.apache.log4j.Logger;
import org.jboss.reddeer.swt.api.Shell;
import org.jboss.reddeer.swt.condition.ShellWithTextIsActive;
import org.jboss.reddeer.swt.handler.WidgetHandler;
import org.jboss.reddeer.swt.util.Display;
import org.jboss.reddeer.swt.wait.WaitWhile;

/**
 * Abstract class for all Shells
 * 
 * @author Jiri Peterka
 * 
 */
public abstract class AbstractShell implements Shell {

	protected final Logger log = Logger.getLogger(this.getClass());

	protected org.eclipse.swt.widgets.Shell swtShell;

	@Override
	public String getText() {
		String text = WidgetHandler.getInstance().getText(swtShell);
		return text;
	}

	@Override
	public void close() {
		String text = getText();
		log.info("Closing shell " + text);

		Display.syncExec(new Runnable() {

			@Override
			public void run() {
				swtShell.close();
			}
		});

		new WaitWhile(new ShellWithTextIsActive(text));
	}
	
}
