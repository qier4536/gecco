package com.geccocrawler.gecco.utils.sub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.geccocrawler.gecco.annotation.XPath;
import com.geccocrawler.gecco.annotation.XPathObjct;
import com.geccocrawler.gecco.spider.XmlBean;

public class SubXmlBean implements XmlBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XPath("/root/title")
	private String title;
	@XPath("/root/header") 
	private String header; // 不存在的xml
	@XPathObjct(rootPath = "/root/list")
	private List<String> lsDrugs = new ArrayList<>();
	@XPathObjct(rootPath = "/root/list1", listPath = "./sub1")
	private List<String> lsSub1 = new ArrayList<>();
	@XPathObjct(rootPath = "/root/location")
	private List<SubXmlBean2> lsXml = new ArrayList<>();
	@XPathObjct(rootPath = "/root/map")
	private Map<String, Object> mapInfo = new HashMap<>();
	@XPathObjct(rootPath = "/root/primaryLocation")
	private SubXmlBean2 xmlBean;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getLsDrugs() {
		return lsDrugs;
	}

	public void setLsDrugs(List<String> lsDrugs) {
		this.lsDrugs = lsDrugs;
	}

	public List<String> getLsSub1() {
		return lsSub1;
	}

	public void setLsSub1(List<String> lsSub1) {
		this.lsSub1 = lsSub1;
	}

	public List<SubXmlBean2> getLsXml() {
		return lsXml;
	}

	public void setLsXml(List<SubXmlBean2> lsXml) {
		this.lsXml = lsXml;
	}

	public Map<String, Object> getMapInfo() {
		return mapInfo;
	}

	public void setMapInfo(Map<String, Object> mapInfo) {
		this.mapInfo = mapInfo;
	}

	public SubXmlBean2 getXmlBean() {
		return xmlBean;
	}

	public void setXmlBean(SubXmlBean2 xmlBean) {
		this.xmlBean = xmlBean;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

}
