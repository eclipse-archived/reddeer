package org.jboss.reddeer.eclipse.test.m2e.core.ui.wizard;

import static org.junit.Assert.*;

import org.jboss.reddeer.eclipse.jdt.ui.ProjectExplorer;
import org.jboss.reddeer.eclipse.m2e.core.ui.wizard.MavenProjectWizard;
import org.jboss.reddeer.eclipse.m2e.core.ui.wizard.MavenProjectWizardArchetypePage;
import org.jboss.reddeer.eclipse.m2e.core.ui.wizard.MavenProjectWizardArchetypeParametersPage;
import org.jboss.reddeer.eclipse.m2e.core.ui.wizard.MavenProjectWizardArtifactPage;
import org.jboss.reddeer.eclipse.m2e.core.ui.wizard.MavenProjectWizardPage;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RedDeerSuite.class)
public class MavenProjectWizardTest {
	
	@AfterClass
	public static void clean(){
		ProjectExplorer pe = new ProjectExplorer();
		pe.open();
		pe.deleteAllProjects();
	}
	
	@Test
	public void createMavenProject(){
		MavenProjectWizard mw = new MavenProjectWizard();
		mw.open();
		mw.next();
		MavenProjectWizardArchetypePage mp1 = new MavenProjectWizardArchetypePage();
		assertEquals("All Catalogs",mp1.getArchetypeCatalog());
		mp1.selectArchetypeCatalog("Internal");
		assertEquals("Internal",mp1.getArchetypeCatalog());
		mp1.selectArchetype("org.apache.maven.archetypes","maven-archetype-quickstart",null);
		mw.next();
		MavenProjectWizardArchetypeParametersPage mp2 = new MavenProjectWizardArchetypeParametersPage();
		mp2.setArtifactId("artifact");
		mp2.setGroupId("group");
		mp2.setVersion("1.0.0");
		assertEquals("artifact",mp2.getArtifactId());
		assertEquals("group",mp2.getGroupId());
		assertEquals("1.0.0",mp2.getVersion());
		assertEquals("group.artifact",mp2.getPackage());
		mw.finish();
		ProjectExplorer pe = new ProjectExplorer();
		pe.open();
		assertTrue(pe.containsProject("artifact"));
	}
	
	public void createCleanMavenProject(){
		MavenProjectWizard mw = new MavenProjectWizard();
		mw.open();
		MavenProjectWizardPage mp = new MavenProjectWizardPage();
		mp.createSimpleProject(true);
		mw.next();
		MavenProjectWizardArtifactPage ma = new MavenProjectWizardArtifactPage();
		ma.setGroupId("g");
		ma.setArtifactId("a");
		mw.finish();
		ProjectExplorer pe = new ProjectExplorer();
		pe.open();
		assertTrue(pe.containsProject("a"));
	}

}
