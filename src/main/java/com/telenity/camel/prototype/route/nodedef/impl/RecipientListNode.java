package com.telenity.camel.prototype.route.nodedef.impl;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.impl.RecipientListNodeBuilder;
import com.telenity.camel.prototype.route.builder.impl.ToNodeBuilder;
import com.telenity.camel.prototype.route.nodedef.RouteNode;

public class RecipientListNode implements RouteNode{

	private String uri;

	public RecipientListNode(String uri){
		this.uri = uri;
	}
	
	@Override
	public RouteNodeBuilder getRouteNodeBuilder() {
		return new RecipientListNodeBuilder(uri);
	}

	public String toString(){
		return "recipientList (" + uri +  ")"; 
	}
}
