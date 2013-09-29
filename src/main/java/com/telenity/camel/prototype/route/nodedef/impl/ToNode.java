package com.telenity.camel.prototype.route.nodedef.impl;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.impl.ToNodeBuilder;
import com.telenity.camel.prototype.route.nodedef.RouteNode;

public class ToNode implements RouteNode{

	private String uri;

	public ToNode(String uri){
		this.uri = uri;
	}
	
	@Override
	public RouteNodeBuilder getRouteNodeBuilder() {
		return new ToNodeBuilder(uri);
	}
	
	public String toString(){
		return "to (" + uri +  ")"; 
	}
	
}
