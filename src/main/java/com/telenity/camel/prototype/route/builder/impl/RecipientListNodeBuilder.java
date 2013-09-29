package com.telenity.camel.prototype.route.builder.impl;

import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.language.SimpleExpression;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;

public class RecipientListNodeBuilder implements RouteNodeBuilder {

	private String uri;

	public RecipientListNodeBuilder(String uri){
		this.uri = uri;
	}
	
	@Override
	public void build(RouteDefinition routeDefinition) {
		routeDefinition.recipientList(new SimpleExpression(uri));
	}

}
