package com.telenity.camel.prototype.route.builder.impl;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.language.SimpleExpression;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;

public class ToSOAPNodeBuilder implements RouteNodeBuilder{

	private String soapText;
	private String soapAction;
	private String url;
	
	public ToSOAPNodeBuilder(String url, String soapText, String soapAction){
		this.url = url;
		this.soapText = soapText;
		this.soapAction = soapAction;
	}
	@Override
	public void build(RouteDefinition routeDefinition) {
		
		//is removing all headers dangerous?
		routeDefinition.removeHeaders("*");
		routeDefinition.setHeader(Exchange.HTTP_METHOD, new SimpleExpression("POST"));
		routeDefinition.setBody(new SimpleExpression(soapText));
		routeDefinition.setHeader("SOAPAction", new SimpleExpression(soapAction));
		//TODO: Content type should be configurable
		routeDefinition.setHeader("Content-Type", new SimpleExpression("text/xml; charset=utf-8"));
		routeDefinition.to(url);		
	}
}
