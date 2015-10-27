package org.jboss.reddeer.junit.internal.runner.statement;

import java.util.ArrayList;
import java.util.List;

import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.junit.extensionpoint.IAfterTest;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

/**
 * 
 * Statement which runs {@link IAfterTest#runAfterTestClass(Object)} methods of
 * defined extensions. Upon failure a screenshot is captured.
 * 
 * @author Lucia Jelinkova
 *
 */
public class RunIAfterClassExtensions extends AbstractStatementWithScreenshot {

	private static final Logger log = Logger.getLogger(RunIAfterClassExtensions.class);

	private final List<IAfterTest> afters;

	public RunIAfterClassExtensions(String config, Statement next, TestClass testClass, 
			List<IAfterTest> afters) {
		super(config, next, testClass, null, null);
		this.afters = afters;
	}

	@Override
	public void evaluate() throws Throwable {
		List<Throwable> errors = new ArrayList<Throwable>();

		try {
			nextStatement.evaluate();
		} catch (Throwable e) {
			errors.add(e);
		} 

		IAfterTest after = null;


		log.debug("Run after class extensions for test class " + testClass.getJavaClass().getName());
		for (IAfterTest each : afters) {
			after = each;
			try {
				if (after.hasToRun()){
					log.debug("Run method runAfterTestClass() of class " + after.getClass().getCanonicalName());
					after.runAfterTestClass(config, testClass);
				}
			} catch (Throwable e) {
				log.error("Run method runAfterTestClass() of class " + after.getClass().getCanonicalName() + " failed", e);
				createScreenshot("AfterClassExt", after.getClass());
				errors.add(e);
			}
		}
		MultipleFailureException.assertEmpty(errors);
	}
}
