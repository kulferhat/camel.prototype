package com.telenity.camel.prototype.route.nodedef.impl;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;
import com.telenity.camel.prototype.route.builder.impl.SetHeaderNodeBuilder;
import com.telenity.camel.prototype.route.builder.util.ExpressionBuilder;
import com.telenity.camel.prototype.route.nodedef.RouteNode;


public class SetHeaderNode implements RouteNode{
	
	private String header;
	private String expression;
	private ExpressionBuilder.ExpressionType type;

	public SetHeaderNode(String header, String expression, ExpressionBuilder.ExpressionType type){
		this.header = header;
		this. expression = expression;
		this.type = type;
	}

	@Override
	public RouteNodeBuilder getRouteNodeBuilder() {
		return new SetHeaderNodeBuilder(header, expression, type);
	}
	
	public String toString(){
		return "setHeader (" + header + " , " + type + "("+  expression + ")" +  ")"; 
	}

}
