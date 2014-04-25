package org.jboss.reddeer.requirements.server.apache.tomcat;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.reddeer.requirements.server.IServerFamily;
import org.jboss.reddeer.requirements.server.IServerReqConfig;


/**
 * 
 * @author Pavol Srna 
 *
 */

@XmlRootElement(name="server-requirement", namespace="http://www.jboss.org/NS/ServerReq")
public class ServerRequirementConfig implements IServerReqConfig {
	
	private String runtime;
	
	@XmlElementWrapper(name="type", namespace="http://www.jboss.org/NS/ServerReq")
	@XmlElements({
		@XmlElement(name="familyApacheTomcat", namespace="http://www.jboss.org/NS/ServerReq", type = FamilyApacheTomcat.class)
	})
	private List<IServerFamily> family;
	
	@Override
	public IServerFamily getServerFamily(){
		return this.family.get(0); //always: size() == 1 
	}
	
	@Override
	public String getRuntime() {
		return runtime;
	}

	@XmlElement(namespace="http://www.jboss.org/NS/ServerReq")
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	
	public boolean equals(Object arg) {
		if(arg == null || !(arg instanceof ServerRequirementConfig))
			return false;
		if(arg == this)
			return true;
		ServerRequirementConfig conf = (ServerRequirementConfig) arg;
		IServerFamily family1 = this.getServerFamily();
		IServerFamily family2 = conf.getServerFamily();
		if(!runtime.equals(conf.runtime) || (family1 == null && family2 != null))
			return false;
		return family1.getLabel().equals(family2.getLabel()) && family1.getVersion().equals(family2.getVersion());
	}
}