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
package org.eclipse.reddeer.junit.internal.configuration.setter;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.eclipse.reddeer.junit.configuration.RedDeerConfigurationException;
import org.eclipse.reddeer.junit.internal.configuration.entity.Property;
import org.eclipse.reddeer.junit.internal.configuration.entity.PropertyBasedConfiguration;
import org.eclipse.reddeer.junit.requirement.Requirement;

/**
 * Sets all  properties from {@link PropertyBasedConfiguration} to the requirement instance. It requires
 * the setters for the properties to pre present and follow java bean conventions.  
 * 
 * @author Lucia Jelinkova
 *
 */
public class ConfigurationSetter {

	/**
	 * Sets the 
	 *
	 * @param requirement the requirement
	 * @param configuration the configuration
	 */
	public void set(Requirement<?> requirement, PropertyBasedConfiguration configuration){
		for (Property property : configuration.getProperties()){
			inject(requirement, property);
		}
	}
	
	private void inject(Requirement<?> requirement, Property property) {
		inject(requirement, property.getKey(), property.getValue());
	}
	
	private void inject(Requirement<?> requirement, String key, Object value){
		PropertyUtilsBean utils = new PropertyUtilsBean();
		try {
			utils.setSimpleProperty(requirement, key, value);
		} catch (IllegalAccessException e) {
			throw createCannotSetPropertyException(key, value, requirement.getClass(), e);
		} catch (InvocationTargetException e) {
			throw createCannotSetPropertyException(key, value, requirement.getClass(), e);
		} catch (NoSuchMethodException e) {
			throw createCannotSetPropertyException(key, value, requirement.getClass(), e);
		}
	}
	
	private RedDeerConfigurationException createCannotSetPropertyException(String key, Object value, Class<?> requirementClass, Exception e){
		return new RedDeerConfigurationException("Cannot set value " + value + " to the property " + key + " into instance of class " + requirementClass, e);
	}
}
