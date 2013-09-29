package com.telenity.camel.prototype.route.nodedef.impl;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.impl.FromRESTNodeBuilder;
import com.telenity.camel.prototype.route.nodedef.RouteNode;

public class FromRESTNode implements RouteNode{

	private String baseUrl;
	private String resourceUrl;
	private boolean matchOnUriPrefix;
	
	public FromRESTNode(String baseUrl, String resourceUrl, boolean matchOnUriPrefix){
		this.baseUrl = baseUrl;
		this.resourceUrl = resourceUrl;
		this.matchOnUriPrefix = matchOnUriPrefix;
	}
	
	@Override
	public RouteNodeBuilder getRouteNodeBuilder() {
		return new FromRESTNodeBuilder(baseUrl, resourceUrl, matchOnUriPrefix);
	}
	
	public String toString(){
		return "from (" + baseUrl +  resourceUrl +  ")"; 
	}

}
