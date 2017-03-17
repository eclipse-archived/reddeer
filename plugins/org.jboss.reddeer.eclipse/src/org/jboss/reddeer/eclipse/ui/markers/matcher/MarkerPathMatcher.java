/******************************************************************************* 
 * Copyright (c) 2016 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.reddeer.eclipse.ui.markers.matcher;

import org.hamcrest.Matcher;
import org.jboss.reddeer.eclipse.ui.views.markers.AbstractMarkersSupportView.Column;

/**
 * Marker matcher for column Path of a marker.
 * 
 * @author mlabuda@redhat.com
 * @since 0.7
 */
public class MarkerPathMatcher extends AbstractMarkerMatcher {
	
	/**
	 * Creates a new marker matcher matching to whole text of Path column.
	 * 
	 * @param text whole Path column text of a marker to match
	 */
	public MarkerPathMatcher(String text) {
		super(text);
	}
	
	/**
	 * marker a new problem matcher matching with matcher for Path column.
	 * 
	 * @param matcher matcher to match Path column of a marker
	 */
	public MarkerPathMatcher(Matcher<String> matcher) {
		super(matcher);
	}

	@Override
	public Column getColumn() {
		return Column.PATH;
	}
}
