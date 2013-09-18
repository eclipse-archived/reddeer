package org.jboss.reddeer.junit.integration.runner.order;

import java.util.Iterator;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.jboss.reddeer.junit.integration.runner.order.suite.RequirementsSuite;
import org.jboss.reddeer.junit.internal.configuration.SuiteConfiguration;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public abstract class TestSequenceRedDeerSuite extends RedDeerSuite {

	protected static final String LOCATIONS_ROOT_DIR = "src/test/resources/org/jboss/reddeer/junit/integration/runner/order";
	
	public TestSequenceRedDeerSuite(Class<?> clazz, RunnerBuilder builder)
			throws InitializationError {
		super(heck(clazz), builder);
	}

	protected TestSequenceRedDeerSuite(Class<?> clazz, RunnerBuilder builder,
			SuiteConfiguration config) throws InitializationError {
		super(clazz, builder, config);
	}
	
	protected abstract List<?> getExpectedSequence();
	
	@Override
	public void run(RunNotifier notifier) {
		TestSequence.getRealSequence().clear();
		super.run(notifier);
		if (!getExpectedSequence().equals(TestSequence.getRealSequence())){
			notifier.fireTestFailure(new Failure(Description.createSuiteDescription(RequirementsSuite.class), createException()));
		}
	}

	private Throwable createException() {
		Iterator<?> realSequenceIterator = TestSequence.getRealSequence().iterator();
		Iterator<?> expectedSequenceIterator = getExpectedSequence().iterator();

		int i = 0;
		while (realSequenceIterator.hasNext() && expectedSequenceIterator.hasNext()){
			Object real = realSequenceIterator.next();
			Object expected = expectedSequenceIterator.next();
			if (!real.equals(expected)){
				return new AssertionFailedError("Expected and real sequence differ at index " + i + ".  Expected: " + expected + ", real: " + real);
			}
			i++;
		}

		if (realSequenceIterator.hasNext()){
			return new AssertionFailedError("Real sequence contains more items than the expected one");
		} else {
			return new AssertionFailedError("Expected sequence contains more items than the real one");
		}
	}
	
	/**
	 * Hecky hook for setting the system property.
	 * @param clazz
	 * @return
	 */
	private static Class<?> heck(Class<?> clazz){
		System.setProperty(SuiteConfiguration.PROPERTY_CONFIG_LOC, LOCATIONS_ROOT_DIR);
		return clazz;
	}
}
