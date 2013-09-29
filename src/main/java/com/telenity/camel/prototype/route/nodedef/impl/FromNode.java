package com.telenity.camel.prototype.route.nodedef.impl;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.impl.FromNodeBuilder;
import com.telenity.camel.prototype.route.nodedef.RouteNode;

public class FromNode implements RouteNode{

	private String uri;
	public FromNode(String uri){
		this.uri = uri;
	}
	
	@Override
	public RouteNodeBuilder getRouteNodeBuilder() {
		return new FromNodeBuilder(uri);
	}
	
	public String toString(){
		return "from (" + uri + ")"; 
	}

}
