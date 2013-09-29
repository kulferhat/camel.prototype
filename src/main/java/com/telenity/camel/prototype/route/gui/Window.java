package com.telenity.camel.prototype.route.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;

import org.apache.camel.impl.DefaultTracedRouteNodes;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RouteDefinition;

import com.telenity.camel.prototype.route.Locator;
import com.telenity.camel.prototype.route.RouteContainer;
import com.telenity.camel.prototype.route.ServiceRoute;
import com.telenity.camel.prototype.route.nodedef.RouteNode;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.io.IOException;

public class Window {

	public JFrame frmApacheCamelPrototype;
	private FromDialog fromDialog;
	private FromRestDialog fromRestDialog ;
	private NewRouteDialog newRouteDialog ;
	private RecipientListDialog recipientListDialog ;
	private SetBodyDialog setBodyDialog;
	private SetHeaderDialog setHeaderDialog;
	private SetPropertyDialog setPropertyDialog;
	private ToDialog toDialog;
	private ToSOAPDialog toSoapDialog;
	
	private JList routeList;
	private JLabel lblRouteName;
	private JLabel lblStatus;
	private JTree routeTree;
	
	private JPanel panelRouteFlow;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmApacheCamelPrototype.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
		initializeDialogs();
	}

	private void initializeDialogs() {
		fromDialog = new FromDialog();
		fromRestDialog = new FromRestDialog();
		newRouteDialog = new NewRouteDialog();
		recipientListDialog = new RecipientListDialog();
		setBodyDialog = new SetBodyDialog();
		setHeaderDialog = new SetHeaderDialog();
		setPropertyDialog = new SetPropertyDialog();
		toDialog = new ToDialog();
		toSoapDialog = new ToSOAPDialog();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmApacheCamelPrototype = new JFrame();
		frmApacheCamelPrototype.setTitle("Apache Camel Prototype");
		frmApacheCamelPrototype.setBounds(100, 100, 812, 583);
		frmApacheCamelPrototype.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmApacheCamelPrototype.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		panel.add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new GridLayout(3, 4, 0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelHeader.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Route Name : ");
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblRouteName = new JLabel("");
		panel_3.add(lblRouteName);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelHeader.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Status : ");
		panel_4.add(lblNewLabel_2);
		
		lblStatus = new JLabel("");
		panel_4.add(lblStatus);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelHeader.add(panel_5);
		
		JLabel lblNewLabel_3 = new JLabel("Min Exec Time :");
		panel_5.add(lblNewLabel_3);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_6.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panelHeader.add(panel_6);
		
		JLabel lblNewLabel_4 = new JLabel("Max Exec Time:");
		panel_6.add(lblNewLabel_4);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panelHeader.add(panel_7);
		
		JLabel lblMeanExecTime = new JLabel("Mean Exec Time :");
		panel_7.add(lblMeanExecTime);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_8.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panelHeader.add(panel_8);
		
		JLabel lblLastExecTime = new JLabel("Last Exec Time:");
		panel_8.add(lblLastExecTime);
		
		panelRouteFlow = new JPanel();
		panel.add(panelRouteFlow, BorderLayout.CENTER);
		panelRouteFlow.setLayout(new BorderLayout(0, 0));
		
		routeTree = new JTree(new DefaultTreeModel(new DefaultMutableTreeNode("route")));
		routeTree.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		panelRouteFlow.add(routeTree);
		
		JPopupMenu routeTreeMenu = new JPopupMenu();
		addPopup(routeTree, routeTreeMenu);
		
		JMenuItem mntmRemoveNode = new JMenuItem("Remove Node");
		mntmRemoveNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object node = routeTree.getSelectionPath().getLastPathComponent();
				if (node != null){
					DefaultMutableTreeNode mnode = (DefaultMutableTreeNode) node;
					RouteNode routeNode = (RouteNode)mnode.getUserObject();
					String routeId = routeTree.getSelectionPath().getPathComponent(0).toString();
					ServiceRoute serviceRoute = Locator.getInstance().getRouteContainer().getRoute(routeId);
					serviceRoute.removeRouteNode(routeNode);
					updateRouteDisplay();
				}
			}
		});
		routeTreeMenu.add(mntmRemoveNode);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		frmApacheCamelPrototype.getContentPane().add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(10, 0, 0, 0));
		
		JButton btnFrom = new JButton("From");
		btnFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fromDialog.setVisible(true);
			}
		});
		
		JLabel lblRouteElements = new JLabel("Route Elements");
		lblRouteElements.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRouteElements.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblRouteElements);
		panel_1.add(btnFrom);
		
		JButton btnFromRest = new JButton("From REST");
		btnFromRest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fromRestDialog.setVisible(true);
			}
		});
		panel_1.add(btnFromRest);
		
		JButton btnTo = new JButton("To");
		btnTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toDialog.setVisible(true);
			}
		});
		panel_1.add(btnTo);
		
		JButton btnToSaop = new JButton("To SOAP");
		btnToSaop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toSoapDialog.setVisible(true);
			}
		});
		panel_1.add(btnToSaop);
		
		JButton btnRecipient = new JButton("Recipient");
		btnRecipient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recipientListDialog.setVisible(true);
			}
		});
		panel_1.add(btnRecipient);
		
		JButton btnSetBody = new JButton("Set Body");
		btnSetBody.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBodyDialog.setVisible(true);
			}
		});
		panel_1.add(btnSetBody);
		
		JButton btnSetHeader = new JButton("Set Header");
		btnSetHeader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setHeaderDialog.setVisible(true);
			}
		});
		panel_1.add(btnSetHeader);
		
		JButton btnSetProperty = new JButton("Set Property");
		btnSetProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPropertyDialog.setVisible(true);
			}
		});
		panel_1.add(btnSetProperty);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmApacheCamelPrototype.getContentPane().add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		routeList = new JList();
		routeList.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		
		routeList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				updateRouteDisplay();
			}
		});
		DefaultListModel listModel = new DefaultListModel();
		routeList.setModel(listModel);
		
		panel_2.add(routeList);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(routeList, popupMenu);
		
		JMenuItem mntmTest = new JMenuItem("Add New Route");
		mntmTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newRouteDialog.setVisible(true);
			}
		});
		popupMenu.add(mntmTest);
		
		JMenuItem mntmTest_1 = new JMenuItem("Start Route");
		mntmTest_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedRouteId = (String) routeList.getSelectedValue();
				if (selectedRouteId != null){
					
					ModelCamelContext camelContext = Locator.getInstance().getCamelContext();
					if (camelContext.getRouteStatus(selectedRouteId) != null && camelContext.getRouteStatus(selectedRouteId).isStarted()){
						System.out.println(selectedRouteId + " is already running.");
						return;
					}
					
					try{
						RouteDefinition routeDef = camelContext.getRouteDefinition(selectedRouteId);
						if (routeDef != null){
							camelContext.removeRouteDefinition(routeDef);
							camelContext.removeRoute(selectedRouteId);
						}
						
						camelContext.removeRoute(selectedRouteId);
						
						RouteContainer routeContainer = Locator.getInstance().getRouteContainer();
						ServiceRoute route = routeContainer.getRoute(selectedRouteId);
						RouteDefinition routeDefinition = route.buildRouteDefinition();
						camelContext.addRouteDefinition(routeDefinition);
						camelContext.startRoute(selectedRouteId);							
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
					
					updateRouteDisplay();
				}
			}
		});
		popupMenu.add(mntmTest_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Stop Route");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedRouteId = (String) routeList.getSelectedValue();
				if (selectedRouteId != null){
					
					ModelCamelContext camelContext = Locator.getInstance().getCamelContext();
					if (camelContext.getRouteStatus(selectedRouteId).isStopped()){
						System.out.println(selectedRouteId + " is already stopped.");
						return;
					}
					
					try{
						camelContext.stopRoute(selectedRouteId);
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
					
					updateRouteDisplay();
				}
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmShowJconsole = new JMenuItem("Show JConsole");
		mntmShowJconsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("jconsole");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		popupMenu.add(mntmShowJconsole);
		
		JLabel lblNewLabel = new JLabel("<html>---- Routes ----<br><br>  Start by right click  </html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel, BorderLayout.NORTH);
	}
	public JList getRouteList() {
		return routeList;
	}

	private void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				updateRouteDisplay();
				
			}
		});
	}
	
	public void updateRouteDisplay(){
		String selectedRouteId = (String) routeList.getSelectedValue();
		if (selectedRouteId != null){
			lblRouteName.setText(selectedRouteId);
			
			
			ModelCamelContext camelContext = Locator.getInstance().getCamelContext();
			
			if (camelContext.getRouteStatus(selectedRouteId) != null && camelContext.getRouteStatus(selectedRouteId).isStarted()){
				lblStatus.setText("Running...");
			}
			else{
				lblStatus.setText("Not Running...");
			}
			
			DefaultTreeModel model = (DefaultTreeModel)routeTree.getModel();
			DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
			root.setUserObject(selectedRouteId);
			root.removeAllChildren();
			model.reload(root);
			
			ServiceRoute route = Locator.getInstance().getRouteContainer().getRoute(selectedRouteId);
			List<RouteNode> nodes = route.nodes;
			for (RouteNode node : nodes){
				
				String nodeText = node.toString();
				nodeText = nodeText.length() > 60 ? nodeText.substring(0, 60) + " ..." : nodeText;
				
				
				root.add(new DefaultMutableTreeNode(node));
				model.reload(root);
			}
		}
	}
}
