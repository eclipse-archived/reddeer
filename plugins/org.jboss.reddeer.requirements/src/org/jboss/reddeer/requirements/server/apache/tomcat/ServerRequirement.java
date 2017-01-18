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
package org.jboss.reddeer.requirements.server.apache.tomcat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jboss.reddeer.common.logging.Logger;
import org.jboss.reddeer.eclipse.wst.server.ui.wizard.NewServerWizardDialog;
import org.jboss.reddeer.eclipse.wst.server.ui.wizard.NewServerWizardPage;
import org.jboss.reddeer.junit.requirement.CustomConfiguration;
import org.jboss.reddeer.junit.requirement.Requirement;
import org.jboss.reddeer.requirements.server.ConfiguredServerInfo;
import org.jboss.reddeer.requirements.server.IServerReqConfig;
import org.jboss.reddeer.requirements.server.ServerReqBase;
import org.jboss.reddeer.requirements.server.ServerReqState;
import org.jboss.reddeer.requirements.server.apache.tomcat.ServerRequirement.ApacheTomcatServer;
import org.jboss.reddeer.swt.impl.text.DefaultText;


/**
 * 
 * @author Pavol Srna
 *
 */
public class ServerRequirement extends ServerReqBase 
			implements Requirement<ApacheTomcatServer>, CustomConfiguration<ServerRequirementConfig> {

	private static final Logger LOGGER = Logger.getLogger(ServerRequirement.class);
	
	
	private ServerRequirementConfig config;
	private static ConfiguredServerInfo lastServerConfiguration;
	private ApacheTomcatServer server;
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface ApacheTomcatServer {
		
		/**
		 * State.
		 *
		 * @return the server req state
		 */
		ServerReqState state() default ServerReqState.RUNNING;
		
		/**
		 * Cleanup.
		 *
		 * @return true, if successful
		 */
		boolean cleanup() default true;
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.reddeer.junit.requirement.Requirement#canFulfill()
	 */
	@Override
	public boolean canFulfill() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.junit.requirement.Requirement#fulfill()
	 */
	@Override
	public void fulfill() {
		if(lastServerConfiguration == null || !isLastConfiguredServerPresent()) {
			LOGGER.info("Setup server");
			setupServerAdapter();
			lastServerConfiguration = new ConfiguredServerInfo(getServerNameLabelText(), config);
		}
		setupServerState(server.state());
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.requirements.server.ServerReqBase#getServerTypeLabelText(org.jboss.reddeer.requirements.server.IServerReqConfig)
	 */
	@Override
	public String getServerTypeLabelText() {
		return config.getServerFamily().getLabel() + " v" + 
				config.getServerFamily().getVersion() + " Server";
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.reddeer.requirements.server.ServerReqBase#getServerNameLabelText(org.jboss.reddeer.requirements.server.IServerReqConfig)
	 */
	@Override
	public String getServerNameLabelText() {
		return getServerTypeLabelText() + " at localhost";
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.requirements.server.ServerReqBase#getRuntimeNameLabelText(org.jboss.reddeer.requirements.server.IServerReqConfig)
	 */
	@Override
	public String getRuntimeNameLabelText() {
		return config.getServerFamily().getCategory() + " " + 
				config.getServerFamily().getLabel() + " v" + 
				config.getServerFamily().getVersion() + " Runtime";
	}
	
	private void setupServerAdapter(){
		
		NewServerWizardDialog swd = new NewServerWizardDialog();
		swd.open();
		
		NewServerWizardPage swpage = new NewServerWizardPage();
		
		swpage.selectType(config.getServerFamily().getCategory(), getServerTypeLabelText());
		swpage.setName(getServerNameLabelText());
		swd.next();
		
		new DefaultText(0).setText(getRuntimeNameLabelText());
		new DefaultText(1).setText(config.getRuntime());
	
		swd.finish();
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.reddeer.junit.requirement.Requirement#setDeclaration(java.lang.annotation.Annotation)
	 */
	@Override
	public void setDeclaration(ApacheTomcatServer server) {
		this.server = server;
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.junit.requirement.CustomConfiguration#getConfigurationClass()
	 */
	@Override
	public Class<ServerRequirementConfig> getConfigurationClass() {
		return ServerRequirementConfig.class;
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.junit.requirement.CustomConfiguration#setConfiguration(java.lang.Object)
	 */
	@Override
	public void setConfiguration(ServerRequirementConfig config) {
		this.config = config;
	}

	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public ServerRequirementConfig getConfig() {
		return this.config;
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.junit.requirement.Requirement#cleanUp()
	 */
	@Override
	public void cleanUp() {
		if(server.cleanup() && config != null){
			removeLastRequiredServerAndRuntime();
			lastServerConfiguration = null;
		}
	}

	@Override
	public ConfiguredServerInfo getConfiguredConfig() {
		return lastServerConfiguration;
	}	
}