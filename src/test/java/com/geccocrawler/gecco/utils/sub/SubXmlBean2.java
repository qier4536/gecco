package com.geccocrawler.gecco.utils.sub;

import com.geccocrawler.gecco.annotation.XPath;
import com.geccocrawler.gecco.spider.XmlBean;

public class SubXmlBean2 implements XmlBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XPath("./name")
	private String name;
	@XPath("./email")
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
