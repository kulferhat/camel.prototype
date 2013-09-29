package com.telenity.camel.prototype.route.builder.impl;

import org.apache.camel.model.RouteDefinition;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;

public class FromNodeBuilder implements RouteNodeBuilder {

	private String uri;
	
	public FromNodeBuilder(String uri){
		this.uri = uri;
	}
	
	@Override
	public void build(RouteDefinition routeDefinition) {
		routeDefinition.from(uri);
	}

}
