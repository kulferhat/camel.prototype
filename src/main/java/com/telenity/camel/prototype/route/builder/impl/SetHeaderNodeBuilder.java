package com.telenity.camel.prototype.route.builder.impl;

import org.apache.camel.model.RouteDefinition;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.util.ExpressionBuilder;

public class SetHeaderNodeBuilder implements RouteNodeBuilder {

	private String header;
	private String expression;
	private ExpressionBuilder.ExpressionType type;

	public SetHeaderNodeBuilder(String header, String expression, ExpressionBuilder.ExpressionType type){
		this.header = header;
		this. expression = expression;
		this.type = type;
	}

	
	@Override
	public void build(RouteDefinition routeDefinition) {
		routeDefinition.setHeader(header, ExpressionBuilder.getExpression(expression, type));
	}

}
