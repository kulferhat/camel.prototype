package com.telenity.camel.prototype.route.builder.impl;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.language.SimpleExpression;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.util.ExpressionBuilder;

public class SetPropertyNodeBuilder implements RouteNodeBuilder {

	private String property;
	private String expression;
	private ExpressionBuilder.ExpressionType type;

	public SetPropertyNodeBuilder(String property, String expression, ExpressionBuilder.ExpressionType type){
		this.property = property;
		this. expression = expression;
		this.type = type;
	}

	
	@Override
	public void build(RouteDefinition routeDefinition) {
		routeDefinition.setProperty(property, ExpressionBuilder.getExpression(expression, type));		
	}

}
