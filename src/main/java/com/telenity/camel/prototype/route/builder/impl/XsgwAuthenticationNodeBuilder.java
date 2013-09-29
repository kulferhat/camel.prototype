package com.telenity.camel.prototype.route.builder.impl;

import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.language.SimpleExpression;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;

public class XsgwAuthenticationNodeBuilder implements RouteNodeBuilder {

	private String xsgwAuthenticationUrl;
	
	public XsgwAuthenticationNodeBuilder(String xsgwAuthenticationUrl){
		this.xsgwAuthenticationUrl = xsgwAuthenticationUrl;
	}
	
	@Override
	public void build(RouteDefinition routeDefinition) {
		//Make sure required header parameters are exist.
		routeDefinition.recipientList(new SimpleExpression("xsgw-auth:" + xsgwAuthenticationUrl + "?spId=${header.spId}&variantId=${header.variantId}&password=${header.password}"));		
	}

}
