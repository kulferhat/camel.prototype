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
import com.telenity.camel.prototype.route.builder.util.ExpressionBuilder;
import com.telenity.camel.prototype.route.nodedef.impl.SetPropertyNode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetPropertyDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtProperty;
	private JTextArea txtExpression ;
	private JComboBox comboBox ;
	
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
	public SetPropertyDialog() {
		setTitle("Set Property");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		{
			JLabel lblProperty = new JLabel("Property : ");
			contentPanel.add(lblProperty);
		}
		{
			txtProperty = new JTextField();
			txtProperty.setText("");
			txtProperty.setColumns(30);
			contentPanel.add(txtProperty);
		}
		{
			JLabel lblNewLabel = new JLabel("Expression :");
			contentPanel.add(lblNewLabel);
		}
		{
			txtExpression = new JTextArea();
			txtExpression.setRows(10);
			txtExpression.setColumns(30);
			contentPanel.add(txtExpression);
		}
		{
			JLabel lblResourcePath = new JLabel("Expression Type : ");
			contentPanel.add(lblResourcePath);
		}
		{
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"SIMPLE", "XPATH", "JXPATH", "GROOVY"}));
			contentPanel.add(comboBox);
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
						route.addRouteNode(new SetPropertyNode(txtProperty.getText(), txtExpression.getText(), 
								ExpressionBuilder.ExpressionType.valueOf((String)comboBox.getSelectedItem())));
						
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
