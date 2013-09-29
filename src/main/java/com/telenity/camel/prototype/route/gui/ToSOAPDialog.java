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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import com.telenity.camel.prototype.route.Locator;
import com.telenity.camel.prototype.route.ServiceRoute;
import com.telenity.camel.prototype.route.nodedef.impl.ToSOAPNode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ToSOAPDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUri;
	private JTextField txtSoapAction;
	private JTextArea txtExpression;
	
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
	public ToSOAPDialog() {
		setTitle("To SOAP");
		setBounds(100, 100, 494, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		{
			JLabel lblNewLabel_2 = new JLabel("URL : ");
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtUri = new JTextField();
			txtUri.setText("");
			contentPanel.add(txtUri);
			txtUri.setColumns(30);
		}
		
		JLabel lblNewLabel_3 = new JLabel("SOAP Action : ");
		contentPanel.add(lblNewLabel_3);
		
		txtSoapAction = new JTextField();
		txtSoapAction.setText("");
		contentPanel.add(txtSoapAction);
		txtSoapAction.setColumns(25);
		{
			JLabel lblNewLabel = new JLabel("SOAP Envelope : ");
			contentPanel.add(lblNewLabel);
		}
		{
			txtExpression = new JTextArea();
			txtExpression.setRows(10);
			txtExpression.setColumns(30);
			contentPanel.add(txtExpression);
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
						route.addRouteNode(new ToSOAPNode(txtUri.getText(), txtExpression.getText(), txtSoapAction.getText()));
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
