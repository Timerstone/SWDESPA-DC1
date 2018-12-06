package designchallenge1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.*;

public class NewEvent{

	private JPanel contentPane;
	private JTextField txtEventName;
	private String addEvent;
	private JFrame frmmain;
	private CalendarProgram mainprog;
	//public CalendarProgram cp;

	/**
	 * Create the frame.
	 */
	public NewEvent(CalendarProgram main) {
		mainprog = main;
		
		frmmain = new JFrame();
		frmmain.setTitle("Add Event");
		frmmain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmmain.setBounds(100, 100, 307, 341);
		frmmain.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmmain.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox cmbMonth = new JComboBox();
		cmbMonth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cmbMonth.setBounds(109, 11, 48, 32);
		contentPane.add(cmbMonth);
		
		JComboBox cmbYear = new JComboBox();
		cmbYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbYear.addItem("Yearly");
		for(int i = 1918; i <= 2118; i++)
				cmbYear.addItem(i);
		cmbYear.setSelectedIndex(100);
		cmbYear.setBounds(109, 97, 75, 37);
		contentPane.add(cmbYear);
		
		JComboBox cmbDay = new JComboBox();
		cmbDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				cmbDay.removeAllItems();
				
				if(cmbMonth.getSelectedIndex() == 0 || cmbMonth.getSelectedIndex() == 2 || cmbMonth.getSelectedIndex() == 4 || cmbMonth.getSelectedIndex() == 6 || 
						cmbMonth.getSelectedIndex() == 7 || cmbMonth.getSelectedIndex() == 9 || cmbMonth.getSelectedIndex() == 11) {
					
					for(int i = 1; i <= 31; i++) {
						cmbDay.addItem(i);
					}
						
					
				}
				else if(cmbMonth.getSelectedIndex() == 3 || cmbMonth.getSelectedIndex() == 5 || cmbMonth.getSelectedIndex() == 8 || 
						cmbMonth.getSelectedIndex() == 10) {
					
					for(int i = 1; i <= 30; i++) {
						cmbDay.addItem(i);
					}
					
				}
				else if(cmbMonth.getSelectedIndex() == 1) {
					if((cmbYear.getSelectedIndex() + 2) % 4 == 0) {
						for(int i = 1; i <= 29; i++) {
							cmbDay.addItem(i);
						}
					}
					else
					{
						for(int i = 1; i <= 28; i++) {
							cmbDay.addItem(i);
						}
					}
					
				}
			}
		});
		cmbDay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbDay.setBounds(109, 54, 48, 32);
		contentPane.add(cmbDay);
		
		JLabel lblMonth = new JLabel("Month:");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMonth.setBounds(36, 11, 63, 32);
		contentPane.add(lblMonth);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDay.setBounds(36, 54, 63, 32);
		contentPane.add(lblDay);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYear.setBounds(36, 97, 63, 32);
		contentPane.add(lblYear);
		
		txtEventName = new JTextField();
		txtEventName.setToolTipText("Event");
		txtEventName.setBounds(98, 203, 183, 32);
		contentPane.add(txtEventName);
		txtEventName.setColumns(10);
		
		JLabel lblEventName = new JLabel("Event Name:");
		lblEventName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEventName.setBounds(10, 204, 89, 32);
		contentPane.add(lblEventName);
		
		JComboBox cmbColor = new JComboBox();
		cmbColor.setModel(new DefaultComboBoxModel(new String[] {"Red", "Green", "Blue"}));
		cmbColor.setSelectedIndex(0);
		cmbColor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbColor.setBounds(109, 145, 75, 37);
		contentPane.add(cmbColor);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws NullPointerException {
				
				String clr = "black";
				int day, month, year;
				
				if(cmbColor.getSelectedIndex() == 0)
					clr = "red";
				else if(cmbColor.getSelectedIndex() == 1)
					clr = "green";
				else if(cmbColor.getSelectedIndex() == 2)
					clr = "blue";
			
				month = Integer.valueOf(cmbMonth.getSelectedItem().toString());
				day = Integer.valueOf(cmbDay.getSelectedItem().toString());
				
				if(cmbYear.getSelectedItem().toString().equals("Yearly"))
					year = 0;
				else
					year = Integer.valueOf(cmbYear.getSelectedItem().toString());
					
				mainprog.getMain().getModels().addEvent(new Events(txtEventName.getText(), day, month, year, clr));
				
				mainprog.refreshCalendar(mainprog.monthToday, mainprog.yearToday);
				mainprog.getMain().checkUpdates();
				
				frmmain.dispose();				
				
			}
		});
		btnAddEvent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddEvent.setBounds(36, 259, 105, 32);
		contentPane.add(btnAddEvent);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmmain.dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(151, 259, 105, 32);
		contentPane.add(btnCancel);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblColor.setBounds(36, 145, 63, 32);
		contentPane.add(lblColor);
	}	
}
