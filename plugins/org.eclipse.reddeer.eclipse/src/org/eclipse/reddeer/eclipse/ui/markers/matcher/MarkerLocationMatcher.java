/*******************************************************************************
 * Copyright (c) 2017 Red Hat, Inc and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Red Hat, Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.reddeer.eclipse.ui.markers.matcher;

import org.hamcrest.Matcher;
import org.eclipse.reddeer.eclipse.ui.views.markers.AbstractMarkersSupportView.Column;

/**
 * Marker matcher for column Location of a marker.
 * 
 * @author mlabuda@redhat.com
 * @since 0.7
 */
public class MarkerLocationMatcher extends AbstractMarkerMatcher {

	/**
	 * Creates a new marker matcher matching to whole text of Location column.
	 * 
	 * @param text whole Location column text of a marker to match
	 */
	public MarkerLocationMatcher(String text) {
		super(text);
	}
	
	/**
	 * Creates a new marker matcher matching with matcher for Location column.
	 * 
	 * @param matcher matcher to match Location column of a marker
	 */
	public MarkerLocationMatcher(Matcher<String> matcher) {
		super(matcher);
	}
	
	@Override
	public Column getColumn() {
		return Column.LOCATION;
	}
}
