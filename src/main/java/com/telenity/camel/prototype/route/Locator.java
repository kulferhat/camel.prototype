package com.telenity.camel.prototype.route;

import org.apache.camel.main.Main;
import org.apache.camel.model.ModelCamelContext;

import com.telenity.camel.prototype.route.gui.Window;

public class Locator {
	private static Locator instance = new Locator();
	private Main camelMain;
	private Window gui;
	private RouteContainer routeContainer;
	
	public Main getCamelMain() {
		return camelMain;
	}

	public void setCamelMain(Main camelMain) {
		this.camelMain = camelMain;
	}
	
	public ModelCamelContext getCamelContext(){
		return (ModelCamelContext) camelMain.getCamelContexts().get(0);
	}
	
	public Window getGui() {
		return gui;
	}

	public void setGui(Window gui) {
		this.gui = gui;
	}

	public static Locator getInstance(){
		return instance;
	}

	public RouteContainer getRouteContainer() {
		return routeContainer;
	}

	public void setRouteContainer(RouteContainer routeContainer) {
		this.routeContainer = routeContainer;
	}

	
	
}
