package org.jboss.reddeer.eclipse.condition;

import org.jboss.reddeer.eclipse.wst.server.ui.view.ServersView;
import org.jboss.reddeer.common.condition.WaitCondition;
import org.jboss.reddeer.common.exception.RedDeerException;

/**
 * Returns true, if there is server with specified name
 * 
 * @author Vlado Pakan
 *
 */
public class ServerExists implements WaitCondition {

	private ServersView view;
	
	private String name;
	
	/**
	 * Construct the condition with a given name.
	 * 
	 * @param name Name
	 */
	public ServerExists(String name) {
		this.name = name;
		view = new ServersView();
		view.open();
	}

	@Override
	public boolean test() {
		try{
			view.getServer(this.name);
			return true;
		} catch (RedDeerException ele){
			return false;
		}		
	}
	
	@Override
	public String description() {
		return "there is server with name: " + this.name;
	}
	
}
