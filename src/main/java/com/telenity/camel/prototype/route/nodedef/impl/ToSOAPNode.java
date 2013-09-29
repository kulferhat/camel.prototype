package com.telenity.camel.prototype.route.nodedef.impl;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.impl.ToSOAPNodeBuilder;
import com.telenity.camel.prototype.route.nodedef.RouteNode;

public class ToSOAPNode implements RouteNode{

	private String soapText;
	private String soapAction;
	private String url;
	
	public ToSOAPNode(String url, String soapText, String soapAction){
		this.url = url;
		this.soapText = soapText;
		this.soapAction = soapAction;
	}
	
	@Override
	public RouteNodeBuilder getRouteNodeBuilder() {
		return new ToSOAPNodeBuilder(url, soapText, soapAction);
	}
	
	public String toString(){
		return "to (" + url + ")"; 
	}

}
