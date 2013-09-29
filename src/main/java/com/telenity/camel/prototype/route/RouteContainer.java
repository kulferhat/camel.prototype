package com.telenity.camel.prototype.route;

import java.util.ArrayList;
import java.util.List;

public class RouteContainer {
	List<ServiceRoute> routes = new ArrayList<ServiceRoute>();
	public RouteContainer(){
		
	}
	
	public void addRoute(ServiceRoute route){
		routes.add(route);
	}
	
	public ServiceRoute getRoute(String routeId){
		for (ServiceRoute route : routes){
			if (route.getRouteId().equals(routeId)){
				return route;
			}
		}
		
		return null;
	}
}
