package com.telenity.camel.prototype.route.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import com.telenity.camel.prototype.route.Locator;
import com.telenity.camel.prototype.route.ServiceRoute;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewRouteDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRouteId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewRouteDialog dialog = new NewRouteDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewRouteDialog() {
		setTitle("Add New Route");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		{
			JLabel lblNewLabel = new JLabel("Route ID : ");
			contentPanel.add(lblNewLabel);
		}
		{
			txtRouteId = new JTextField();
			txtRouteId.setText("");
			contentPanel.add(txtRouteId);
			txtRouteId.setColumns(30);
		}
		{
			JLabel lblDescription = new JLabel("Description : ");
			contentPanel.add(lblDescription);
		}
		{
			JTextArea txtRouteDesc = new JTextArea();
			txtRouteDesc.setRows(10);
			txtRouteDesc.setColumns(30);
			contentPanel.add(txtRouteDesc);
		}
		
		JLabel lblNewLabel_1 = new JLabel("<html><a href=\"www.google.com\">See Camel Documentation for details</a></html>");
		contentPanel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String routeId = txtRouteId.getText().trim();
						if(validateRouteId(routeId)){
							ServiceRoute route = new ServiceRoute(routeId);
							Locator.getInstance().getRouteContainer().addRoute(route);
							
							JList routeList = Locator.getInstance().getGui().getRouteList();
							((DefaultListModel)(routeList.getModel())).addElement(routeId);
							int index = ((DefaultListModel)(routeList.getModel())).indexOf(routeId);
							Locator.getInstance().getGui().getRouteList().setSelectedIndex(index);
							
							Locator.getInstance().getGui().updateRouteDisplay();
						}
						setVisible(false);
					}

					private boolean validateRouteId(String routeId) {
						if (routeId.equals("")){
							JOptionPane.showMessageDialog(getContentPane(), "Enter a valid routeId.");
							System.err.println("Enter a valid routeId");
							return false;
						}
						
						ServiceRoute route = Locator.getInstance().getRouteContainer().getRoute(routeId);
						if (route != null){
							JOptionPane.showMessageDialog(getContentPane(), "There is already a route named " + routeId);
							System.err.println("There is already a route named " + routeId);
							return false;
						}
						
						return true;
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
