package com.telenity.camel.prototype.route.builder.impl;

import org.apache.camel.model.RouteDefinition;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.util.ExpressionBuilder;

public class ChoiceNodeBuilder implements RouteNodeBuilder {

	private String property;
	private String expression;
	private ExpressionBuilder.ExpressionType type;

	public ChoiceNodeBuilder(String property, String expression, ExpressionBuilder.ExpressionType type){
		this.property = property;
		this. expression = expression;
		this.type = type;
	}

	
	@Override
	public void build(RouteDefinition routeDefinition) {
		
	}

}
