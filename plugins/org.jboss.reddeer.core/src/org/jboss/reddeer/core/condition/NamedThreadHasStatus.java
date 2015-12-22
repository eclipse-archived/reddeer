package org.jboss.reddeer.core.condition;

import java.util.Iterator;
import java.util.Set;

import org.hamcrest.Matcher;
import org.jboss.reddeer.common.condition.AbstractWaitCondition;

/**
 * Condition is met when thread with matching name has required state
 * 
 * @author vlado pakan
 */
public class NamedThreadHasStatus extends AbstractWaitCondition {

	private Matcher<String> nameMatcher;
	private Thread.State state;
	private boolean returnTrueIfDoesNotExist;
	private Set<Thread> currentThreads = Thread.getAllStackTraces().keySet();
	/**
	 * Condition is met when thread with name matching nameMatcher has state equals to state
	 * In case returnTrueIfDoesNotExist parameter is set to true condition is fulfilled also
	 * when matching thread does not exists 
	 * @param nameMatcher
	 * @param state
	 * @param returnTrueIfDoesNotExist
	 * 
	 */
	public NamedThreadHasStatus(Matcher<String> nameMatcher , Thread.State state, boolean returnTrueIfDoesNotExist) {
		this.nameMatcher = nameMatcher;
		this.state = state;
		this.returnTrueIfDoesNotExist = returnTrueIfDoesNotExist;
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.common.condition.WaitCondition#test()
	 */
	@Override
	public boolean test() {
		currentThreads = Thread.getAllStackTraces().keySet();
		Iterator<Thread> itThread = currentThreads.iterator();
		boolean threadNotFound = true;
		boolean hasState = false;
		while(threadNotFound && itThread.hasNext()){
			Thread thread = itThread.next();
			if (nameMatcher.matches(thread.getName())){
				threadNotFound = false;
				hasState = thread.getState().equals(state);
			}
		}
		return hasState || (threadNotFound && returnTrueIfDoesNotExist);
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.common.condition.AbstractWaitCondition#description()
	 */
	@Override
	public String description() {
		return "thread with name matching" + this.nameMatcher + " has state " + this.state;
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.common.condition.AbstractWaitCondition#errorMessage()
	 */
	@Override
	public String errorMessage() {
		StringBuilder msg = new StringBuilder("The following threads are avaiable:\n");
		for (Thread thread : currentThreads){
			if (thread != null){
				msg.append(thread.getName());
				msg.append(":");
				msg.append(thread.getState());
				msg.append("\n");
			}
		}
		return msg.toString();
	}
}
