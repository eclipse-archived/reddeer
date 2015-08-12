package org.jboss.reddeer.eclipse.test.wst.common.project.facet.ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.jboss.reddeer.eclipse.jdt.ui.packageexplorer.PackageExplorer;
import org.jboss.reddeer.eclipse.test.wst.server.ui.view.ServersViewTestCase;
import org.jboss.reddeer.eclipse.ui.dialogs.ExplorerItemPropertyDialog;
import org.jboss.reddeer.eclipse.ui.wizards.datatransfer.ExternalProjectImportWizardDialog;
import org.jboss.reddeer.eclipse.ui.wizards.datatransfer.WizardProjectsImportPage;
import org.jboss.reddeer.eclipse.utils.DeleteUtils;
import org.jboss.reddeer.eclipse.wst.common.project.facet.ui.FacetsPropertyPage;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RedDeerSuite.class)
public class FacetsPropertyPageTest {

	private static final String PROJECT = "server-project";
	private static final String FACET1 = "Java";
	private static final String FACET1_VERSION = "1.7";

	@Before
	public void createProject() {
		ExternalProjectImportWizardDialog wizard = new ExternalProjectImportWizardDialog();
		wizard.open();

		WizardProjectsImportPage wizardPage = new WizardProjectsImportPage();
		wizardPage.setArchiveFile(ServersViewTestCase.ZIP_FILE
				.getAbsolutePath());
		wizardPage.selectProjects(PROJECT);

		wizard.finish();

		PackageExplorer packageExplorer = new PackageExplorer();
		packageExplorer.open();
	}

	@After
	public void cleanup() {
		ExplorerItemPropertyDialog dialog = new ExplorerItemPropertyDialog(new PackageExplorer().getProject(PROJECT));
		if (dialog.isOpen()) {
			dialog.cancel();
		}
		PackageExplorer explorer = new PackageExplorer();
		explorer.open();
		DeleteUtils.forceProjectDeletion(explorer.getProject(PROJECT), true);
	}

	@Test
	public void selectFacet() {
		ExplorerItemPropertyDialog dialog = new ExplorerItemPropertyDialog(new PackageExplorer().getProject(PROJECT));
		FacetsPropertyPage facetsPage = new FacetsPropertyPage();
		
		dialog.open();
		dialog.select(facetsPage);
		facetsPage.selectFacet(FACET1);
		facetsPage.apply();
		dialog.ok();

		dialog.open();
		dialog.select(facetsPage);
		assertThat(facetsPage.getSelectedFacets().get(0).getText(),
				is(FACET1));

		dialog.ok();
	}

	@Test
	public void selectVersion() {
		ExplorerItemPropertyDialog dialog = new ExplorerItemPropertyDialog(new PackageExplorer().getProject(PROJECT));
		FacetsPropertyPage facetsPage = new FacetsPropertyPage();
		
		dialog.open();
		dialog.select(facetsPage);
		facetsPage.selectFacet(FACET1);
		facetsPage.selectVersion(FACET1, FACET1_VERSION);
		facetsPage.apply();
		dialog.ok();
		
		dialog.open();
		dialog.select(facetsPage);
		assertThat(facetsPage.getSelectedVersion(FACET1), is(FACET1_VERSION));

		dialog.ok();
	}
}