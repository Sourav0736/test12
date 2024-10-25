package ran;
import javax.swing.*;
import javax.swing.border.Border;

import ran.BaseUI.DBConnection;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class billgen{	

	public static double calculateBill(double units) {
        double bill = 0;
        
        if (units <= 100) {
            bill = units * 1.5;
        } else if (units <= 300) {
            bill = (100 * 1.5) + ((units - 100) * 2.0);
        } else {
            bill = (100 * 1.5) + (200 * 2.0) + ((units - 300) * 3.0);
        }

        double fixedCharge = 50.0;
        bill += fixedCharge;

        return bill;
    }
	  public static long calculateDifference(LocalDate startDate, LocalDate endDate) {
	        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
	        
	      
	         return daysBetween;
	    }

	    // Function to add or subtract days to/from a date
	    public static LocalDate addDaysToDate(LocalDate date, int daysToAdd) {
	        return date.plusDays(daysToAdd);
	    }

	    // Function to subtract months from a date
	    public static LocalDate subtractMonthsFromDate(LocalDate date, int monthsToSubtract) {
	        return date.minusMonths(monthsToSubtract);
	    }

	    // Function to get the current date
	    public static LocalDate getCurrentDate() {
	        return LocalDate.now();
	    }

	    // Function to format a date in a specific pattern
	    public static String formatDate(LocalDate date, String pattern) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	        return date.format(formatter);
	    }
	    
	    
	    public static boolean addConsumptionData(String username, double units, LocalDate initialDate, LocalDate finalDate,LocalDate dueDate) {
	        boolean isInserted = false;
	        Connection conn = DBConnection.getConnection();
	        
	        String query = "INSERT INTO user_detail (user_name, Units_consumed, initial_date, finale_date, due_date, payment_status) VALUES (?, ?, ?, ?, ?, ?)";
	        
	        try {
	            PreparedStatement statement = conn.prepareStatement(query);
	            statement.setString(1, username);
	            statement.setDouble(2, units);

	            // Convert string dates to SQL-compatible format (yyyy-MM-dd)
	            
	            statement.setDate(3, java.sql.Date.valueOf(initialDate));
	            statement.setDate(4, java.sql.Date.valueOf(finalDate));
	            statement.setDate(5, java.sql.Date.valueOf(dueDate));
	            statement.setString(6,"not paid");
	            
	            int rowsAffected = statement.executeUpdate();
	            if (rowsAffected > 0) {
	                isInserted = true;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBConnection.closeConnection(conn);
	        }
	        
	        return isInserted;
	    }
	    public static void deleteUsersWithNullDate() {
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            // 1. Establish a connection (replace with your database credentials)
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/billboarddb", "root", "guipassword123");

	            // 2. Prepare the SQL DELETE statement
	            String sql = "DELETE FROM user_detail WHERE Units_consumed = null";

	            // 3. Create a PreparedStatement object to execute the query
	            pstmt = conn.prepareStatement(sql);

	            // 4. Execute the delete statement
	            int rowsDeleted = pstmt.executeUpdate();

	            // 5. Output the result
	            System.out.println(rowsDeleted + " row(s) deleted.");

	        } catch (SQLException e) {
	            e.printStackTrace();  // Handle SQL exceptions
	        } finally {
	            // 6. Close the resources to prevent memory leaks
	            try {
	                if (pstmt != null) pstmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	    public static void zeroUsersWithNullDate() {
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            // 1. Establish a connection (replace with your database credentials)
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/billboarddb", "root", "guipassword123");

	            // 2. Prepare the SQL DELETE statement
	            String sql = "DELETE FROM user_detail WHERE Units_consumed = 0";

	            // 3. Create a PreparedStatement object to execute the query
	            pstmt = conn.prepareStatement(sql);

	            // 4. Execute the delete statement
	            int rowsDeleted = pstmt.executeUpdate();

	            // 5. Output the result
	            System.out.println(rowsDeleted + " row(s) deleted.");

	        } catch (SQLException e) {
	            e.printStackTrace();  // Handle SQL exceptions
	        } finally {
	            // 6. Close the resources to prevent memory leaks
	            try {
	                if (pstmt != null) pstmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	    
}

public class login extends billgen{ 
	
	    String username;
		JComboBox box;
		JComboBox box1;
		JComboBox box2;
		JComboBox box3;
		JComboBox box4;
		JComboBox box5;
		JFrame frame = new JFrame();
	    JPanel panel = new JPanel();
	    JPanel panel1 = new JPanel();
	    JPanel panel2 = new JPanel();
	    JPanel panel3 = new JPanel();
	    JPanel panel4 = new JPanel();
	    JLabel label = new JLabel();
	    JLabel label1 = new JLabel();
	    JLabel label2 = new JLabel();
	    JLabel label3 = new JLabel();
	    JLabel label4 = new JLabel();
	    JLabel label5 = new JLabel();
	    JLabel label6 = new JLabel();
	    JLabel label7 = new JLabel();
	    JLabel label8 = new JLabel();
	    JLabel label9 = new JLabel();
	    JLabel label10 = new JLabel();
	    JLabel label11 = new JLabel();
	    JLabel label12 = new JLabel();
	    JLabel label13 = new JLabel();
	    JLabel label14 = new JLabel();
	    JLabel label15 = new JLabel();
	    JLabel label16 = new JLabel();
	    
	    JButton button = new JButton();
	    JButton button1 = new JButton();
	    JButton button2 = new JButton();
	    JTextField passwordField = new JTextField();
	    JTextField textbox = new JTextField(); 
	    JTextField textbox1 = new JTextField(); 
	    ImageIcon image = new ImageIcon("C:\\Users\\soura\\OneDrive\\Pictures\\Screenshots\\logo1.jpg");
	    ImageIcon image1 = new ImageIcon("guiback.jpg");
	    
        
		login(String username){
	    	this.username = username;
	    	System.out.println(username);
			Integer year[]= {2024,2025,2026,2027};
			String[] month = {"Month","01","02","03","04","05","06","07","08","09","10","11","12"};
			Integer [] day = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
	String[] Box = new String[year.length + 1];
	        
	        // Add a default string as the first item
	Box[0] = "Year";
    
    // Convert the integer array to strings and populate the comboBoxItems array
    for (int i = 0; i < year.length; i++) {
        Box[i + 1] = String.valueOf(year[i]);
    }
	        
	        
	String[] Box1 = new String[day.length + 1];
	        
	        // Add a default string as the first item
	        Box1[0] = "Day";
	        
	        // Convert the integer array to strings and populate the comboBoxItems array
	        for (int i = 0; i < day.length; i++) {
	            Box1[i + 1] = String.valueOf(day[i]);
	        }
	        Date dueDate = null;
            String paymentStatus = null;
            Double price = null;
	        
			box = new JComboBox(Box1);
			box1 = new JComboBox(month);
			box2 = new JComboBox(Box);
			box3= new JComboBox(Box1);
			box4 = new JComboBox(month);
			box5 = new JComboBox(Box);
			Connection conn = DBConnection.getConnection();
	        
	        String query = "SELECT Units_consumed, due_date, payment_status FROM user_detail WHERE user_name = ?";
	        
	        try {
	            PreparedStatement statement = conn.prepareStatement(query);
	            statement.setString(1, username);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            // If user with the provided username exists
	            if (resultSet.next()) {
	                dueDate = resultSet.getDate("due_date");
	                paymentStatus = resultSet.getString("payment_status");
	                price = resultSet.getDouble("Units_consumed");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBConnection.closeConnection(conn);
	        }
	        
	        
		        frame.setLayout(new FlowLayout(FlowLayout.LEADING));
		        panel.setLayout(null);
		        panel.setPreferredSize(new Dimension(400,350));
		        panel.setBackground(null);
		        panel1.setLayout(null);
		        panel1.setPreferredSize(new Dimension(980,50));
		        panel1.setBackground(null); 
		        panel2.setLayout(null);
		        panel2.setPreferredSize(new Dimension(575,350));
		        panel2.setBackground(null);
		        panel3.setLayout(null);
		        panel3.setPreferredSize(new Dimension(980,50));
		        panel3.setBackground(null);
		        panel4.setLayout(null);
		        panel4.setPreferredSize(new Dimension(980,50));
		        panel4.setBackground(null);
		        label3.setBounds(110, 34,400,100);
		        label4.setBounds(200,15,400,100);
		        Border lineBorder = BorderFactory.createLineBorder(Color.gray, 2);
		        LocalDate today = getCurrentDate();

		        // Set the border to the JPanel
		        panel.setBorder(lineBorder);
		        panel1.setBorder(lineBorder);
		        panel2.setBorder(lineBorder);
		        panel3.setBorder(lineBorder);
		        panel4.setBorder(lineBorder);
		        //error label 5,6
		        label5.setBounds(105,87,400,100);
		        label6.setBounds(200, 17,400,100);
		        label5.setVisible(false);
		        label6.setVisible(false);

		        label.setBounds(350,10,400,50);
		        label1.setBounds(30, 157,400,100);
		        label2.setBounds(30, 17,400,100);
		        label7.setBounds(30,315,400,100);
		        label9.setBounds(50,0,400,100);
		        label10.setBounds(50,50,400,100);
		        label11.setBounds(50,100,400,100);
		        label12.setBounds(50,150,400,100);
		        label13.setBounds(30,10,300,50);
		        label14.setBounds(810,10,300,50);
		        label15.setBounds(500,350,400,100);
		        label16.setBounds(500,350,400,100);

		        button.setBounds(30,290,340,35);
		        label8.setBounds(30,87,400,100);
		        textbox.setBounds(30,100,400,35);
		        passwordField.setBounds(30,80,340,35);
		        box3.setBounds(30,150,100,35);
		        box4.setBounds(150,150,100,35);
		        box5.setBounds(270,150,100,35);
		        box.setBounds(30,220,100,35);
	            box1.setBounds(150,220,100,35);
		        box2.setBounds(270,220,100,35);
		        button1.setBounds(30,205,110,25);
		       
		        button.addActionListener(e ->{ 
		        	frame.getContentPane().revalidate();
		        	String day1 = (String) box.getSelectedItem();
	            String month1 = (String) box1.getSelectedItem();
	            String year1 = (String) box2.getSelectedItem();
	            String day2 = (String) box3.getSelectedItem();
	            String month2 = (String) box4.getSelectedItem();
	            String year2 = (String) box5.getSelectedItem();
	            

		        	if(passwordField.getText().equals("") || day1.equals("Day") || month1.equals("Month") || year1.equals("Year")) {
		        		label6.setText("- please enter UNITS ");
		        		label1.setForeground(new Color(0xFF7F7F));
			            label8.setForeground(new Color(0xFF7F7F)); 
			            label2.setForeground(new Color(0xFF7F7F));      
				        label6.setVisible(true);
				        label3.setVisible(false);
				        label4.setVisible(false);
		        	}
		        else{
		        	if(!passwordField.getText().matches("[0-9]+")) {
				        label6.setText("- please enter valid UNITS ");
				        label6.setVisible(true);
		        	}
		        	else {
		        try {	
		        label1.setForeground(new Color(0xEDEADE));
		            label2.setForeground(new Color(0xEDEADE));
		            label8.setForeground(new Color(0xEDEADE));
		            label5.setVisible(false);
		        label6.setVisible(false);
		        label3.setVisible(true);
		        label4.setVisible(true);
		        double units = Integer.parseInt(passwordField.getText());
//		        		calculateBill(Integer.parseInt(passwordField.getText()));


		        LocalDate initialDate = LocalDate.of(Integer.parseInt(year1),Integer.parseInt(month1), Integer.parseInt(day1));
		        LocalDate finalDate = LocalDate.of(Integer.parseInt(year2),Integer.parseInt(month2), Integer.parseInt(day2));
		        LocalDate dueDate1 = addDaysToDate(finalDate, 5);
		        System.out.println(initialDate);
		        System.out.println(finalDate);
		        long diffdays = calculateDifference(finalDate,initialDate);
		        if(diffdays > 0) {
		        	label5.setVisible(false);
			        System.out.println(diffdays);
			        deleteUsersWithNullDate();
			        zeroUsersWithNullDate();
			        addConsumptionData(username,units,initialDate,finalDate,dueDate1);
			        frame.dispose();
			        new login(username);
		        }
		        else {
		        	label5.setVisible(true);
		        	label1.setForeground(new Color(0xFF7F7F));	            
	            label8.setForeground(new Color(0xFF7F7F)); 
		        }}
		       catch(DateTimeException e1){
		    	   System.out.println(e1);
		    	   label1.setForeground(new Color(0xFF7F7F));	            
		            label8.setForeground(new Color(0xFF7F7F)); 
		       }
		        	}}});

		        
		        label1.setText("Final Date");
		        label2.setText("POWER CONSUMED(kWh)");
		        label6.setText("- please enter UNITS ");
		        label5.setText("- invalid date ");
		        label7.setText("Already have an account?");
		        label8.setText("Initial Date");
		        label9.setText("DUE DATE: "+dueDate);
		        label10.setText("PAYMENT STATUS: "+paymentStatus);
		        label11.setText("UNITS CONSUMED: "+price);
		        label12.setText("PRICE: "+calculateBill(price));
		        label13.setText("ELECTRICITY BILLING");
		        label14.setText("DATE: "+today+" 🔵");
		       
		        box.setSelectedItem("Day");
		        box1.setSelectedItem("Month");
		        box2.setSelectedItem("Year");
		        box3.setSelectedItem("Day");
		        box4.setSelectedItem("Month");
		        box5.setSelectedItem("Year");
		        

		        button.setText("Create new Bill");
		        button1.setText("Forgot Password?");
		        button2.setText("Login");
		        button.setFocusable(false);
		        button1.setFocusable(false);
		        button2.setFocusable(false);
		        box.setFocusable(false);
		        box1.setFocusable(false);
		        box2.setFocusable(false);
		        box3.setFocusable(false);
		        box4.setFocusable(false);
		        box5.setFocusable(false);
		        // label.setPreferredSize(new Dimension(400,100));
		        // button.setPreferredSize(new Dimension(200,25));
		        // textbox.setPreferredSize(new Dimension(200,25));
		        // textbox1.setPreferredSize(new Dimension(200,25));

		        // frame.setUndecorated(true);
		        frame.setTitle("Home page");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        // frame.setResizable(false);
		        frame.setSize(1000,900);
		        label3.setText("");
		        label4.setText("*");
		        label.setForeground(new Color(0xFAF9F6));
		        button1.setForeground(new Color(0x3399FF));
		        button1.setBackground(new Color(0x36393e));
		        button2.setForeground(new Color(0x3399FF));
		        button2.setBackground(new Color(0x36393e));
		        label1.setForeground(new Color(0xEDEADE));
		        label2.setForeground(new Color(0xEDEADE));  
		        label3.setForeground(new Color(0xFF7F7F)); 
		        label4.setForeground(new Color(0xFF7F7F)); 
		        label5.setForeground(new Color(0xFF7F7F));
		        label6.setForeground(new Color(0xFF7F7F)); 
		        label7.setForeground(new Color(0x707378));
		        label8.setForeground(new Color(0xEDEADE));
		        label9.setForeground(new Color(0xEDEADE));
		        label10.setForeground(new Color(0xEDEADE));
		        label11.setForeground(new Color(0xEDEADE));
		        label12.setForeground(new Color(0xEDEADE));
		        label13.setForeground(new Color(0xEDEADE));
		        label14.setForeground(new Color(0xEDEADE));
		        label15.setForeground(new Color(0xEDEADE));
		        
		        
		        button.setFont(new Font("Uni Sans", Font.PLAIN,15));
		        button1.setFont(new Font("Uni Sans", Font.PLAIN,12));
		        button2.setFont(new Font("Uni Sans", Font.PLAIN,12));
		        button1.setHorizontalAlignment(JButton.LEFT);
		        button2.setHorizontalAlignment(JButton.LEFT);
		        label.setFont(new Font("Uni Sans", Font.BOLD,20));
		        label1.setFont(new Font("Uni Sans", Font.BOLD,13));
		        label2.setFont(new Font("Uni Sans", Font.BOLD,13));
		        label8.setFont(new Font("Uni Sans", Font.BOLD,13));
		        label5.setFont(new Font("Uni Sans", Font.ITALIC,12));
		        label6.setFont(new Font("Uni Sans", Font.ITALIC,12));
		        label7.setFont(new Font("Uni Sans", Font.BOLD,12));
		        label9.setFont(new Font("Uni Sans", Font.BOLD,15));
		        label10.setFont(new Font("Uni Sans", Font.BOLD,15));
		        label11.setFont(new Font("Uni Sans", Font.BOLD,15));
		        label12.setFont(new Font("Uni Sans", Font.BOLD,15));
		        label13.setFont(new Font("Uni Sans", Font.BOLD,20));
		        label14.setFont(new Font("Uni Sans", Font.BOLD,15));
		        textbox.setFont(new Font("Uni Sans", Font.PLAIN,13));
		        textbox1.setFont(new Font("Uni Sans", Font.PLAIN,13));
		        box.setFont(new Font("Uni Sans", Font.PLAIN,13));
		        box1.setFont(new Font("Uni Sans", Font.PLAIN,13));
		        passwordField.setFont(new Font("Uni Sans", Font.PLAIN,13));
		        
		        panel.setBackground(new Color(0x36393e));
		        panel1.setBackground(new Color(0x36393e));
		        panel2.setBackground(new Color(0x36393e));
		        
		        button.setForeground(new Color(0xEDEADE));
		        button.setBackground(new Color(0x5A65E9));
		        textbox.setForeground(new Color(0xEDEADE));
		        
		        box.setBackground(new Color(0x1e2124));
		        box.setForeground(new Color(0xEDEADE));
		        box1.setBackground(new Color(0x1e2124));
		        box1.setForeground(new Color(0xEDEADE));
		        box2.setBackground(new Color(0x1e2124));
		        box2.setForeground(new Color(0xEDEADE));
		        box3.setBackground(new Color(0x1e2124));
		        box3.setForeground(new Color(0xEDEADE));
		        box4.setBackground(new Color(0x1e2124));
		        box4.setForeground(new Color(0xEDEADE));
		        box5.setBackground(new Color(0x1e2124));
		        box5.setForeground(new Color(0xEDEADE));
		      
		        textbox1.setCaretColor(new Color(0xEDEADE));
		        passwordField.setCaretColor(new Color(0xEDEADE));
		        textbox.setCaretColor(new Color(0xEDEADE));
		        
		        
		        passwordField.setForeground(new Color(0xEDEADE));
		        textbox.setBackground(new Color(0x1e2124));
		        
		        textbox1.setBackground(new Color(0x1e2124));
		        textbox1.setCaretColor(new Color(0xEDEADE));
		        passwordField.setBackground(new Color(0x1e2124));
		        
		        button.setBorder(BorderFactory.createBevelBorder(2));
		        button1.setBorder(BorderFactory.createBevelBorder(2));
		        button2.setBorder(BorderFactory.createBevelBorder(2));
		        textbox.setBorder(BorderFactory.createBevelBorder(2));
		        textbox1.setBorder(BorderFactory.createBevelBorder(2));
		        passwordField.setBorder(BorderFactory.createBevelBorder(2));
		        box.setBorder(BorderFactory.createBevelBorder(2));
		        box1.setBorder(BorderFactory.createBevelBorder(2));
		        box2.setBorder(BorderFactory.createBevelBorder(2));
		        // textbox.setPreferredSize(new Dimension(250,40));
		        label.setText("Welcome back "+username+" !");      
		        frame.setIconImage(image.getImage());
		        frame.getContentPane().setBackground(new Color(0x36393e));
		        frame.setVisible(true);

		        panel.add(button);
//		        panel.add(button1);
		        panel.add(box);
		        panel.add(box1);
		        panel.add(box2);
		        panel.add(box3);
		        panel.add(box4);
		        panel.add(box5);
		        panel.add(button2);
//		        panel.add(label);
		        panel.add(label1);
		        panel.add(label2);
		      
		        panel.add(passwordField);
		        panel.add(label3);
		        panel.add(label4);
		        panel.add(label5);
		        panel.add(label6);
//		        panel.add(label7);
		        panel.add(label8);
		        panel1.add(label13);
		        panel1.add(label14);
		        panel3.add(label);
		        panel2.add(label9);
		        panel2.add(label10);
		        panel2.add(label11);
		        panel2.add(label12);
		        
		        frame.add(panel3);
		        frame.add(panel1);	        
		        frame.add(panel);
		        frame.add(panel2);
		        
		        frame.add(panel4);
		        
		        frame.setVisible(true);

		}

}
