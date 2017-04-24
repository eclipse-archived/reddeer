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
package org.eclipse.reddeer.junit.test.integration.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="java-requirement", namespace="http://www.jboss.org/NS/JavaReq")
public class JavaRequirementConfig {

	private String version;
	
	private String runtime;

	public String getVersion() {
		return version;
	}

	@XmlElement(namespace="http://www.jboss.org/NS/JavaReq")
	public void setVersion(String version) {
		this.version = version;
	}

	public String getRuntime() {
		return runtime;
	}

	@XmlElement(namespace="http://www.jboss.org/NS/JavaReq")
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
}
