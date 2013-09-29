package com.telenity.camel.prototype.route;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.apache.camel.main.Main;

import com.telenity.camel.prototype.route.builder.util.ExpressionBuilder.ExpressionType;
import com.telenity.camel.prototype.route.gui.Window;
import com.telenity.camel.prototype.route.nodedef.impl.FromNode;
import com.telenity.camel.prototype.route.nodedef.impl.FromRESTNode;
import com.telenity.camel.prototype.route.nodedef.impl.SetBodyNode;
import com.telenity.camel.prototype.route.nodedef.impl.SetHeaderNode;
import com.telenity.camel.prototype.route.nodedef.impl.SetPropertyNode;
import com.telenity.camel.prototype.route.nodedef.impl.ToSOAPNode;

public class CamelPrototype {
	
	public CamelPrototype(){
		
	}
	
	private void init() throws Exception{
		initGui();
		
		initCamel();
		
		initRouteContainer();
		
		initSampleRoutes();
	}
	
	private void initSampleRoutes() {
		
		initPingPong();
		
		initSampleSOAP();
		
		initMashupService();
		
	}

	private void initMashupService() {
		String routeId = "SampleMashup";
		
		ServiceRoute xsgwRoute = new ServiceRoute(routeId);
		xsgwRoute.addRouteNode(new FromRESTNode("http://127.0.0.1:8097/", "ipquery/{ip}", true));
		
		String soapRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.cdyne.com/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ws:ResolveIP>\n" + 
				"         <!--Optional:-->\n" + 
				"         <ws:ipAddress>${property.ip}</ws:ipAddress>\n" + 
				"         <!--Optional:-->\n" + 
				"         <ws:licenseKey>test</ws:licenseKey>\n" + 
				"      </ws:ResolveIP>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
		
		xsgwRoute.addRouteNode(new ToSOAPNode("http://ws.cdyne.com/ip2geo/ip2geo.asmx", soapRequest, "http://ws.cdyne.com/ResolveIP"));
		xsgwRoute.addRouteNode(new SetPropertyNode("country", "//*[local-name()='Country']/text()", ExpressionType.XPATH));
		
		soapRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:coun=\"http://www.ezzylearning.com/services/CountryInformationService.asmx\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <coun:GetCurrencyCodeByCountry>\n" + 
				"         <!--Optional:-->\n" + 
				"         <coun:countryName>${property.country}</coun:countryName>\n" + 
				"      </coun:GetCurrencyCodeByCountry>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
		
		xsgwRoute.addRouteNode(new ToSOAPNode("http://www.ezzylearning.com/services/CountryInformationService.asmx", soapRequest, "http://www.ezzylearning.com/services/CountryInformationService.asmx/GetCurrencyCodeByCountry"));
		xsgwRoute.addRouteNode(new SetPropertyNode("currency", "//*[local-name()='GetCurrencyCodeByCountryResult']/text()", ExpressionType.XPATH));
		
		String jsonResponse = "{\"country\" : \"${property.country}\" , \"currency\" : \"${property.currency}\"}";
		xsgwRoute.addRouteNode(new SetBodyNode(jsonResponse, ExpressionType.SIMPLE));
		xsgwRoute.addRouteNode(new SetHeaderNode("Content-Type", "application/json", ExpressionType.SIMPLE));
				
		Locator.getInstance().getRouteContainer().addRoute(xsgwRoute);
		
		JList routeList = Locator.getInstance().getGui().getRouteList();
		((DefaultListModel)(routeList.getModel())).addElement(routeId);
		int index = ((DefaultListModel)(routeList.getModel())).indexOf(routeId);
		Locator.getInstance().getGui().getRouteList().setSelectedIndex(index);
		
		Locator.getInstance().getGui().updateRouteDisplay();
	}

	private void initSampleSOAP() {
		String routeId = "SampleSOAP";
		ServiceRoute route = new ServiceRoute(routeId);
		route.addRouteNode(new FromNode("jetty:http://127.0.0.1:8097/samplesoap"));
		
		route.addRouteNode(new SetPropertyNode("message", "Hello World", ExpressionType.SIMPLE));
		
		String soapEnvelope = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <hello>\n" + 
				"         ${property.message}\n" + 
				"      </hello>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
		
		route.addRouteNode(new SetBodyNode(soapEnvelope, ExpressionType.SIMPLE));
		
		Locator.getInstance().getRouteContainer().addRoute(route);
		
		JList routeList = Locator.getInstance().getGui().getRouteList();
		((DefaultListModel)(routeList.getModel())).addElement(routeId);
		int index = ((DefaultListModel)(routeList.getModel())).indexOf(routeId);
		Locator.getInstance().getGui().getRouteList().setSelectedIndex(index);
		
		Locator.getInstance().getGui().updateRouteDisplay();
		
	}

	private void initPingPong() {
		String routeId = "PingPong";
		ServiceRoute route = new ServiceRoute(routeId);
		route.addRouteNode(new FromNode("jetty:http://127.0.0.1:8097/ping"));
		route.addRouteNode(new SetBodyNode("pong", ExpressionType.SIMPLE));
		
		Locator.getInstance().getRouteContainer().addRoute(route);
		
		JList routeList = Locator.getInstance().getGui().getRouteList();
		((DefaultListModel)(routeList.getModel())).addElement(routeId);
		int index = ((DefaultListModel)(routeList.getModel())).indexOf(routeId);
		Locator.getInstance().getGui().getRouteList().setSelectedIndex(index);
		
		Locator.getInstance().getGui().updateRouteDisplay();
	}

	private void initRouteContainer() {
		RouteContainer container = new RouteContainer();
		Locator.getInstance().setRouteContainer(container);
	}

	private void initCamel() throws Exception {
		Main main = new Main();
		main.enableHangupSupport();
		main.start();
		Locator.getInstance().setCamelMain(main);
		//main.run();
	}

	private void initGui(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmApacheCamelPrototype.setVisible(true);
					Locator.getInstance().setGui(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) throws Exception {
		new CamelPrototype().init();
		
		
	}
}
