package com.telenity.camel.prototype.route.nodedef.impl;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.impl.XsgwAuthenticationNodeBuilder;
import com.telenity.camel.prototype.route.nodedef.RouteNode;

public class XsgwAuthenticationNode implements RouteNode{

	private String xsgwAuthenticationUrl;
	
	public XsgwAuthenticationNode(String xsgwAuthenticationUrl){
		this.xsgwAuthenticationUrl = xsgwAuthenticationUrl;
	}
	@Override
	public RouteNodeBuilder getRouteNodeBuilder() {
		return new XsgwAuthenticationNodeBuilder(xsgwAuthenticationUrl);
	}

}
