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
package org.jboss.reddeer.swt.generator.internal.framework.rules.complex;

import java.util.List;

import org.eclipse.swtbot.generator.framework.GenerationComplexRule;
import org.eclipse.swtbot.generator.framework.GenerationSimpleRule;
import org.jboss.reddeer.swt.generator.internal.framework.rules.simple.TreeRule;

public class TreeFilterComplexRule extends GenerationComplexRule{

	private TreeRule tRule;
	
	@Override
	public boolean appliesToPartially(GenerationSimpleRule rule, int i) {
		if(rule instanceof TreeRule){
			if(i == 0){
				tRule = (TreeRule)rule;
			}
			return ((TreeRule)rule).getTreeIndex() == tRule.getTreeIndex();
		}
		return false;
	}

	@Override
	public boolean appliesTo(List<GenerationSimpleRule> rules) {
		for(GenerationSimpleRule r: rules){
			if(r instanceof TreeRule){
				if( ((TreeRule)r).getTreeIndex() != tRule.getTreeIndex()){
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<String> getActions() {
		return getInitializationRules().get(getInitializationRules().size()-1).getActions();
	}

	@Override
	public List<String> getImports() {
		return tRule.getImports();
	}
	
}