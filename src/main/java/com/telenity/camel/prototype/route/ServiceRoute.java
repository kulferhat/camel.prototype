package com.telenity.camel.prototype.route;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.model.RouteDefinition;

import com.telenity.camel.prototype.route.nodedef.RouteNode;

public class ServiceRoute {
	
	public RouteDefinition routeDefinition;
	public String routeId;
	public List<RouteNode> nodes;
	
	public ServiceRoute(String routeId){
		this.routeId = routeId;
		this.nodes = new ArrayList<RouteNode>();
	}
	
	
	public void addRouteNode(RouteNode routeNode){
		nodes.add(routeNode);
	}
	
	public void removeRouteNode(RouteNode routeNode){
		nodes.remove(routeNode);
	}
	
	public RouteDefinition buildRouteDefinition(){
		this.routeDefinition = new RouteDefinition();
		this.routeDefinition.setId(routeId);
		this.routeDefinition.autoStartup(false);
		for (RouteNode node : nodes){
			node.getRouteNodeBuilder().build(routeDefinition);
		}
		
		return routeDefinition;
	}	
	
	public String getRouteId() {
		return routeId;
	}
}
