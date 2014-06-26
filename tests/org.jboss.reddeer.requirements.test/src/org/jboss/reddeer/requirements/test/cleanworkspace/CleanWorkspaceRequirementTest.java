package org.jboss.reddeer.requirements.test.cleanworkspace;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.jboss.reddeer.eclipse.jdt.ui.ide.NewJavaProjectWizardDialog;
import org.jboss.reddeer.eclipse.jdt.ui.packageexplorer.PackageExplorer;
import org.jboss.reddeer.junit.internal.configuration.NullTestRunConfiguration;
import org.jboss.reddeer.junit.internal.requirement.Requirements;
import org.jboss.reddeer.junit.internal.runner.RequirementsRunner;
import org.jboss.reddeer.junit.internal.runner.RequirementsRunnerBuilder;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.jboss.reddeer.requirements.cleanworkspace.CleanWorkspaceRequirement.CleanWorkspace;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;

@RunWith(RedDeerSuite.class)
public class CleanWorkspaceRequirementTest {

	RequirementsRunnerBuilder builder;
	Requirements requirements;

	@Before
	public void setUp() {
		builder = new RequirementsRunnerBuilder(new NullTestRunConfiguration());
		Runner runner = null;
		try {
			runner = builder.runnerForClass(TestClass.class);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if (!(runner instanceof RequirementsRunner)) {
			fail();
		}
		RequirementsRunner reqRunner = (RequirementsRunner) runner;
		requirements = null;
		try {
			Field field = RequirementsRunner.class
					.getDeclaredField("requirements");
			field.setAccessible(true);
			requirements = (Requirements) field.get(reqRunner);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void fulfillWithoutProjectsTest() {
		requirements.fulfill();
	}

	@Test
	public void fulfillWithProjectsTest() {
		NewJavaProjectWizardDialog projectWizard = new NewJavaProjectWizardDialog();
		projectWizard.open();
		projectWizard.getFirstPage().setProjectName("TestProject");
		projectWizard.finish();
		assertFalse("Project should be imported, but isn't",
				new PackageExplorer().getProjects().isEmpty());
	}
	
	@CleanWorkspace
	public static class TestClass{
		
		public TestClass() {
			// TODO Auto-generated constructor stub
		}
		
		@Test
		public void voidTest(){
			
		}
	}
	
}
