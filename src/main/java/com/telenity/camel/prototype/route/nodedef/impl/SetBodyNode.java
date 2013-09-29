package com.telenity.camel.prototype.route.nodedef.impl;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.impl.SetBodyNodeBuilder;
import com.telenity.camel.prototype.route.builder.util.ExpressionBuilder;
import com.telenity.camel.prototype.route.nodedef.RouteNode;

public class SetBodyNode implements RouteNode{

	private String expression;
	private ExpressionBuilder.ExpressionType type;

	public SetBodyNode(String expression, ExpressionBuilder.ExpressionType type){
		this. expression = expression;
		this.type = type;
	}

	@Override
	public RouteNodeBuilder getRouteNodeBuilder() {
		return new SetBodyNodeBuilder(expression, type);
	}

	public String toString(){
		return "setBody (" + type + "("+  expression + ")" +  ")"; 
	}

}
