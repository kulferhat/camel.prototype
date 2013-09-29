package com.telenity.camel.prototype.route.nodedef.impl;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.impl.SetPropertyNodeBuilder;
import com.telenity.camel.prototype.route.builder.util.ExpressionBuilder;
import com.telenity.camel.prototype.route.nodedef.RouteNode;


public class SetPropertyNode implements RouteNode{
	
	private String property;
	private String expression;
	private ExpressionBuilder.ExpressionType type;

	public SetPropertyNode(String property, String expression, ExpressionBuilder.ExpressionType type){
		this.property = property;
		this. expression = expression;
		this.type = type;
	}

	@Override
	public RouteNodeBuilder getRouteNodeBuilder() {
		return new SetPropertyNodeBuilder(property, expression, type);
	}

	public String toString(){
			return "setProperty (" + property + " , " + type + "("+  expression + ")" +  ")"; 
	}
	

}
