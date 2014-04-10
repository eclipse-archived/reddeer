package org.jboss.reddeer.eclipse.ui.browser;

import org.jboss.reddeer.eclipse.condition.BrowserHasURL;
import org.jboss.reddeer.swt.api.Browser;
import org.jboss.reddeer.swt.condition.PageIsLoaded;
import org.jboss.reddeer.swt.exception.SWTLayerException;
import org.jboss.reddeer.swt.impl.browser.InternalBrowser;
import org.jboss.reddeer.swt.wait.TimePeriod;
import org.jboss.reddeer.swt.wait.WaitUntil;
import org.jboss.reddeer.swt.wait.WaitWhile;
import org.jboss.reddeer.workbench.impl.view.WorkbenchView;

/**
 * Represents Internal Browser view in Eclipse
 * 
 * @author psuchy
 * 
 */
public class BrowserView extends WorkbenchView {

	private Browser browser;
	private static final TimePeriod TIMEOUT = TimePeriod.LONG;

	/**
	 * Constructor of BrowserView Class
	 * Check if Internal Web Browser view exists
	 * 
	 * @throws ViewNotFoundException if the view does not exist
	 * @see WorkbenchView
	 */
	public BrowserView() {
		super("Internal Web Browser");
	}
	
	/**
	 * Constructor of BrowserView Class
	 * 
	 * @param browser Browser to reuse
	 */
	public BrowserView(InternalBrowser browser) {
		this();

		this.browser = browser;
	}

	/**
	 * Opens Internal Web Browser view
	 */
	@Override
	public void open() {
		if(!isOpen())
			super.open();

		if (browser == null)
			browser = new InternalBrowser();
	};

	/**
	 * Opens page with given URL in browser
	 * 
	 * @param url
	 */
	public void openPageURL(String url) {
		browser.setURL(url);
		new WaitUntil(new PageIsLoaded(browser), TIMEOUT);
	}

	/**
	 * Refreshes currently opened page in browser
	 */
	public void refreshPage() {
		browser.setURL(browser.getURL());
		new WaitUntil(new PageIsLoaded(browser), TIMEOUT);
	}

	/**
	 * Go to the previous page in browser
	 */
	public void back() {
		String prevUrl = browser.getURL();
		browser.back();
		new WaitWhile(new BrowserHasURL(this, prevUrl), TIMEOUT);
		new WaitUntil(new PageIsLoaded(browser), TIMEOUT);
	}

	/**
	 * Go to the next page in browser
	 */
	public void forward() {
		String prevUrl = browser.getURL();
		browser.forward();
		new WaitWhile(new BrowserHasURL(this, prevUrl), TIMEOUT);
		new WaitUntil(new PageIsLoaded(browser), TIMEOUT);
	}

	/**
	 * Gets URL of the currently opened page
	 * 
	 * @return String URL of the current page
	 */
	public String getPageURL() {
		return browser.getURL();
	}
	
	/**
	 * Gets Text of the currently opened page
	 * 
	 * @return String Text of the current page
	 */
	public String getText() {
		return browser.getText();
	}
	
	/**
	 * Checks if browser is already open
	 */
	public boolean isOpen() {
		/* if InternalBrowser instance exists already, lets assume browser is opened
		 * - either browser was set in BrowserView(InternalBrowser) constructor and therefore
		 * it's opened (caller's responsibility) or it was set in open() */
		if (browser != null)
			return true;

		try{
			new InternalBrowser(); 
			return true;  // browser is already opened
		}catch(SWTLayerException ex){
			return false;
		}
	};

}
