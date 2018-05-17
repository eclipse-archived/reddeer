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
package org.eclipse.reddeer.common.adaptable;

public class MidClass implements RedDeerAdaptable<TopClass>, TopClass {

	private int firstField;
	
	public MidClass(int firstParam) {
		firstField = firstParam;
	}
	
	@Override
	public String getString() {
		return "MidClass";
	}
	
	public int getFirstField() {
		return firstField;
	}
	
	public void setFirstField(int firstField) {
		this.firstField = firstField;
	}
	
	@Override
	public Object[] getAdapterConstructorArguments() {
		return new Object[] {firstField};
	}
	
	@Override
	public Class<?>[] getAdapterConstructorClasses() {
		return new Class<?>[] {int.class};
	}
}
