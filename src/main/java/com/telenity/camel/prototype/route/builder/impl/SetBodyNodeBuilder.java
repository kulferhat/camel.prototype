package com.telenity.camel.prototype.route.builder.impl;

import org.apache.camel.model.RouteDefinition;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.util.ExpressionBuilder;

public class SetBodyNodeBuilder implements RouteNodeBuilder {

	private String expression;
	private ExpressionBuilder.ExpressionType type;

	public SetBodyNodeBuilder(String expression, ExpressionBuilder.ExpressionType type){
		this. expression = expression;
		this.type = type;
	}

	
	@Override
	public void build(RouteDefinition routeDefinition) {
		routeDefinition.setBody(ExpressionBuilder.getExpression(expression, type));
	}

}
