package com.telenity.camel.prototype.route.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.telenity.camel.prototype.route.Locator;
import com.telenity.camel.prototype.route.ServiceRoute;
import com.telenity.camel.prototype.route.nodedef.impl.FromRESTNode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FromRestDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBaseUrl;
	private JTextField txtResourcePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FromDialog dialog = new FromDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FromRestDialog() {
		setTitle("From REST");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		{
			JLabel lblNewLabel = new JLabel("Base URL :");
			contentPanel.add(lblNewLabel);
		}
		{
			txtBaseUrl = new JTextField();
			txtBaseUrl.setText("");
			contentPanel.add(txtBaseUrl);
			txtBaseUrl.setColumns(30);
		}
		{
			JLabel lblResourcePath = new JLabel("Resource Path :");
			contentPanel.add(lblResourcePath);
		}
		{
			txtResourcePath = new JTextField();
			txtResourcePath.setColumns(27);
			contentPanel.add(txtResourcePath);
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
						JList routeList = Locator.getInstance().getGui().getRouteList();
						String selectedRouteId = (String) routeList.getSelectedValue();
						if (selectedRouteId == null){
							JOptionPane.showMessageDialog(getContentPane(), "Select route to add route element ");
							return;
						}
						
						ServiceRoute route = Locator.getInstance().getRouteContainer().getRoute(selectedRouteId);
						route.addRouteNode(new FromRESTNode(txtBaseUrl.getText(), txtResourcePath.getText(), true));
						Locator.getInstance().getGui().updateRouteDisplay();
						setVisible(false);
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
