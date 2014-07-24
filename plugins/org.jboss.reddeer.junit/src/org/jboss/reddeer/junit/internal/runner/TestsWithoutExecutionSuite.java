package org.jboss.reddeer.junit.internal.runner;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/**
 * Suite containing tests without a run.<br/>
 * <br/>
 * 
 * It doesn't execute tests. The suite only informs that there are tests, whose
 * requirements were not fulfilled for any test configuration
 * so the tests were not executed at all.<br/>
 * 
 * @author Radoslav Rabara
 *
 */
public class TestsWithoutExecutionSuite extends Suite {

	/**
	 * Constructs {@link Suite} informing about tests, which were not
	 * executed even once.
	 * 
	 * @param clazz test class or test suite containing test classes
	 * @param testsManager {@link TestsExecutionManager} used to determine
	 * 						if the test classes were executed or not
	 * @throws InitializationError is thrown when the initialization fails
	 */
	public TestsWithoutExecutionSuite(Class<?> clazz, TestsExecutionManager testsManager)
			throws InitializationError {
		super(clazz, new TestsWithoutRunRunnerBuilder(testsManager));
	}
	
	/**
	 * Constructor used for separate independent classes. 
	 * 
	 * @see #TestsWithoutExecutionSuite(Class, TestsExecutionManager)
	 */
	public TestsWithoutExecutionSuite(Class<?>[] classes, TestsExecutionManager testsManager) throws InitializationError {
		super(new TestsWithoutRunRunnerBuilder(testsManager), EmptySuite.class, classes);
	}
	
	
	@Override
	public String getName() {
		return "NOT EXECUTED TESTS";
	}

	@Override
	public String toString() {
		return "Suite '" + this.getClass().getName() + "' "
				+ "showing tests without a single run";
	}

	private static class TestsWithoutRunRunnerBuilder extends RunnerBuilder {

		private TestsExecutionManager testsRunManager;

		public TestsWithoutRunRunnerBuilder(TestsExecutionManager testsManager) {
			this.testsRunManager = testsManager;
		}

		@Override
		public Runner runnerForClass(Class<?> clazz) throws Throwable {
			if (testsRunManager.isExecuted(clazz)) {
				return null;
			}
			return new TestWithoutExecutionRunner(clazz);
		}

	}
}
