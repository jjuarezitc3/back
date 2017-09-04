package com.jjuarez.rest.responseTemplates;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class JsonPeticionRango {
	private String since;
	private String until;
	
	public String getSince() {
		return since;
	}
	public void setSince(String since) {
		this.since = since;
	}
	public String getUntil() {
		return until;
	}
	public void setUntil(String until) {
		this.until = until;
	}
}
