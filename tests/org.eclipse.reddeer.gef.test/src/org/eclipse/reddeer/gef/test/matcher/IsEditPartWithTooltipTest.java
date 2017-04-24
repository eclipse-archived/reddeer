/*******************************************************************************
 * Copyright (c) 2017 Red Hat, Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.reddeer.gef.test.matcher;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.hamcrest.Matcher;
import org.eclipse.reddeer.common.matcher.RegexMatcher;
import org.eclipse.reddeer.gef.matcher.IsEditPartWithTooltip;
import org.junit.Test;

/**
 * Test for IsEditPartWithTooltipTest
 * 
 * @author Andrej Podhradsky (andrej.podhradsky@gmail.com)
 *
 */
public class IsEditPartWithTooltipTest {

	@Test
	public void testMacthingEditPartWithNullTooltip() {
		Matcher<EditPart> matcher = new IsEditPartWithTooltip("helloworld");
		assertFalse(matcher.matches(new AbstractGraphicalEditPart() {
			@Override
			protected IFigure createFigure() {
				return new Figure();
			}

			@Override
			protected void createEditPolicies() {
			}
		}));
	}

	@Test
	public void testMacthingEditPartWithLabelTooltip() {
		Matcher<EditPart> matcher = new IsEditPartWithTooltip("helloworld");
		assertTrue(matcher.matches(new AbstractGraphicalEditPart() {
			@Override
			protected IFigure createFigure() {
				IFigure fig = new Figure();
				fig.setToolTip(new Label("helloworld"));
				return fig;
			}

			@Override
			protected void createEditPolicies() {
			}
		}));
	}

	@Test
	public void testMacthingEditPartWithTextFlowTooltip() {
		Matcher<EditPart> matcher = new IsEditPartWithTooltip("helloworld");
		assertTrue(matcher.matches(new AbstractGraphicalEditPart() {
			@Override
			protected IFigure createFigure() {
				IFigure fig = new Figure();
				fig.setToolTip(new TextFlow("helloworld"));
				return fig;
			}

			@Override
			protected void createEditPolicies() {
			}
		}));
	}

	@Test
	public void testMacthingEditPartWithLabelTooltipByRegex() {
		Matcher<EditPart> matcher = new IsEditPartWithTooltip(new RegexMatcher("hello.*d"));
		assertTrue(matcher.matches(new AbstractGraphicalEditPart() {
			@Override
			protected IFigure createFigure() {
				IFigure fig = new Figure();
				fig.setToolTip(new Label("helloworld"));
				return fig;
			}

			@Override
			protected void createEditPolicies() {
			}
		}));
	}

}
