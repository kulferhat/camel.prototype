package com.telenity.camel.prototype.route.builder.impl;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.RouteDefinition;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.server.Request;

import com.telenity.camel.prototype.route.builder.RouteNodeBuilder;



public class FromRESTNodeBuilder implements RouteNodeBuilder{
	private String baseUrl;
	private String resourceUrl;
	private boolean matchOnUriPrefix;
	
	public FromRESTNodeBuilder(String baseUrl, String resourceUrl, boolean matchOnUriPrefix){
		this.baseUrl = baseUrl;
		this.resourceUrl = resourceUrl;
		this.matchOnUriPrefix = matchOnUriPrefix;
	}

	@Override
	public void build(RouteDefinition routeDefinition) {
		String endpoint = "jetty:" + baseUrl + "?matchOnUriPrefix=" + matchOnUriPrefix;
		System.out.println("-=-------===ASDFASDF=============== " + endpoint);
		routeDefinition.from(endpoint);
		routeDefinition.process(new PathAndQueryParamExtractor(resourceUrl));
	}
	
	//sendsms/301/2251/2cellinfo?sender=90990&recipient=96820000006&message=hello
	static class PathAndQueryParamExtractor implements Processor{

		private String resourceUrl;

		public PathAndQueryParamExtractor(String resourceUrl){
			this.resourceUrl = resourceUrl;
		}
		
		@Override
		public void process(Exchange exchange) throws Exception {
			String path = exchange.getIn().getHeader(Exchange.HTTP_PATH, String.class);
			
			String query = exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class);
						
			System.out.println("1: " + exchange.getIn().getHeader(Exchange.HTTP_BASE_URI));
			System.out.println("2: " + exchange.getIn().getHeader(Exchange.HTTP_CHARACTER_ENCODING));
			System.out.println("3: " + exchange.getIn().getHeader(Exchange.HTTP_CHUNKED));
			System.out.println("4: " + exchange.getIn().getHeader(Exchange.HTTP_METHOD));
			System.out.println("5: " + exchange.getIn().getHeader(Exchange.HTTP_PATH));
			System.out.println("6: " + exchange.getIn().getHeader(Exchange.HTTP_PROTOCOL_VERSION));
			System.out.println("7: " + exchange.getIn().getHeader(Exchange.HTTP_QUERY));
			System.out.println("8: " + exchange.getIn().getHeader(Exchange.HTTP_SERVLET_REQUEST));
			System.out.println("9: " + exchange.getIn().getHeader(Exchange.HTTP_URI));
			System.out.println("10: " + exchange.getIn().getHeader(Exchange.HTTP_URL));
			
			parse(path, query, exchange);
		}
		
		//TODO: Made testable, this method should return property list but not setting properties into exchange, this should be done in caller method
		
		private void parse(String path, String queryExpression, Exchange exchange){
			String[] pathNodes = StringUtils.split(path, "/");
			String[] templateNodes = StringUtils.split(resourceUrl, "/");
			
			if (path != null){
				for (int i = 0; i < pathNodes.length; i++){
					String pathNode = pathNodes[i];
					String templateNode = templateNodes[i];
					
					String key = "";
					String value = pathNode;
					
					if (templateNode.startsWith("{")){
						key = StringUtils.strip(templateNode, "{}");				
					}
					
					exchange.setProperty(key, value);
				}
			}
			
			if (queryExpression != null){
				String[] queryArr = StringUtils.split(queryExpression, "&");
				for (String queryParam : queryArr){
					String[] queryParamParts = StringUtils.split(queryParam, "=");
					String key = queryParamParts[0];
					String value = queryParamParts[1];
					
					exchange.setProperty(key, value);					
				}
			}
		}		
	}
}
