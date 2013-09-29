package com.telenity.camel.prototype.route.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.telenity.camel.prototype.route.Locator;
import com.telenity.camel.prototype.route.ServiceRoute;
import com.telenity.camel.prototype.route.nodedef.impl.FromNode;
import com.telenity.camel.prototype.route.nodedef.impl.ToSOAPNode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FromDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUri;

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
	public FromDialog() {
		setTitle("From");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		{
			JLabel lblNewLabel = new JLabel("URI  :  ");
			contentPanel.add(lblNewLabel);
		}
		{
			txtUri = new JTextField();
			txtUri.setText("");
			contentPanel.add(txtUri);
			txtUri.setColumns(30);
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
						route.addRouteNode(new FromNode(txtUri.getText()));
						
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
