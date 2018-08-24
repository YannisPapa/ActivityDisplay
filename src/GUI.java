import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel panel;
	PieChart pieC;
	BarChart barC;
	LineChart lineC;
	JTextField userSearch;
	public static JTextField dayLine;
	public static JRadioButton speedButton;
	public static JRadioButton distanceButton;
	public static JRadioButton tDistanceButton;

	GUI(String title) {
		super(title);
		this.setLayout(new GridLayout(1, 1, 0, 0));
		this.setResizable(false);
		this.setSize(1750, 1000);

		panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);

		JTextArea userSText = new JTextArea();
		userSText.setText("User ID:");
		userSText.setBackground(getBackground());
		userSText.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userSText.setEditable(false);
		userSText.setBounds(15, 925, 70, 30);
		panel.add(userSText);
		
		userSearch = new JTextField();
		userSearch.setBounds(95, 925, 100, 30);
		panel.add(userSearch);
		
		JTextArea daySText = new JTextArea();
		daySText.setText("Day(LineGraph):");
		daySText.setBackground(getBackground());
		daySText.setFont(new Font("Times New Roman", Font.BOLD, 20));
		daySText.setEditable(false);
		daySText.setBounds(785, 925, 150, 30);
		panel.add(daySText);
		
		dayLine = new JTextField();
		dayLine.setText("dd/mm/yyyy");
		dayLine.setBounds(935, 925, 100, 30);
		panel.add(dayLine);
		
		JButton pieGButton = new JButton("PieChart");
		pieGButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeGraph("Pie");
			}
		});
		pieGButton.setBounds(205, 925, 100, 30);
		panel.add(pieGButton);

		JButton barGButton = new JButton("BarGraph");
		barGButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeGraph("Bar");
			}
		});
		barGButton.setBounds(315, 925, 100, 30);
		panel.add(barGButton);
		
		JButton lineGButton = new JButton("LineGraph");
		lineGButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeGraph("Line");
			}
		});
		lineGButton.setBounds(675, 925, 100, 30);
		panel.add(lineGButton);

		speedButton = new JRadioButton("Speed");
		speedButton.setMnemonic(KeyEvent.VK_B);
		speedButton.setBounds(425, 925, 65, 30);
		speedButton.setSelected(true);
		panel.add(speedButton);
	    
	    distanceButton = new JRadioButton("Distance");
	    distanceButton.setMnemonic(KeyEvent.VK_B);
	    distanceButton.setBounds(490, 925, 75, 30);
	    panel.add(distanceButton);
	    
	    tDistanceButton = new JRadioButton("Total Distance");
	    tDistanceButton.setMnemonic(KeyEvent.VK_B);
	    tDistanceButton.setBounds(565, 925, 110, 30);
	    panel.add(tDistanceButton);
	    
	    ButtonGroup group = new ButtonGroup();
	    group.add(speedButton);
	    group.add(distanceButton);
	    group.add(tDistanceButton);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void changeGraph(String s) {
		if(userSearch.getText().equals("") || userSearch.getText() == null) {
			userIdWarning();
			return;
		}
		if(!ActivityDisplay.usersData.containsKey(userSearch.getText())) {
			userIdInvalid();
			return;
		}
		if (s.equals("Pie")) {
			this.getContentPane().removeAll();
			clearPanel();
			pieC = new PieChart("Daily Activity Breakdown", panel, userSearch.getText());
			this.getContentPane().add(panel);
			this.getContentPane().revalidate();
			this.getContentPane().repaint();
		}
		if (s.equals("Bar")) {
			this.getContentPane().removeAll();
			clearPanel();
			if(GUI.speedButton.isSelected()) {
				barC = new BarChart("Average Speed for Each Activity on each Day", panel, userSearch.getText());		
			} 
			else if(GUI.distanceButton.isSelected()){
				barC = new BarChart("Average Distance for Each Activity on each Day", panel, userSearch.getText());		
			} 
			else {
				barC = new BarChart("Total Distance for Each Activity on each Day", panel, userSearch.getText());			
			}
			this.getContentPane().add(panel);
			this.getContentPane().revalidate();
			this.getContentPane().repaint();
		}
		if (s.equals("Line")) {
			if(!ActivityDisplay.usersData.get(userSearch.getText()).date.contains(dayLine.getText())) {
				userInvalidDay();
				return;
			}
			this.getContentPane().removeAll();
			clearPanel();
			if(GUI.speedButton.isSelected()) {
				lineC = new LineChart("Speed during the day", panel, userSearch.getText());			
			} 
			else if(GUI.distanceButton.isSelected()){
				lineC = new LineChart("Distance during the day", panel, userSearch.getText());			
			} 
			else {
				lineC = new LineChart("Total Distance during the day", panel, userSearch.getText());			
			}
			this.getContentPane().add(panel);
			this.getContentPane().revalidate();
			this.getContentPane().repaint();
		}
	}
	
	private void clearPanel(){
		if(barC != null) {
			panel.remove(barC.chartPanel);
		}
		if(pieC != null) {
			panel.remove(pieC.chartPanel);
		}
		if(lineC != null) {
			panel.remove(lineC.chartPanel);
		}
	}
	
	private static void userIdWarning() {
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	JOptionPane.showMessageDialog(null, "Please enter a User ID.", "Warning", JOptionPane.WARNING_MESSAGE);
	        }
	    });
	}
	
	public static void userIdInvalid() {
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	JOptionPane.showMessageDialog(null, "This User does not exist, Please enter a valid User ID.", "Warning", JOptionPane.WARNING_MESSAGE);
	        }
	    });
	}
	
	public static void userInvalidDay() {
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	JOptionPane.showMessageDialog(null, "This User does not have any Activity on this day.", "Warning", JOptionPane.WARNING_MESSAGE);
	        }
	    });
	}
}
