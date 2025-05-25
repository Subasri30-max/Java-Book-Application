package b;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class app extends Frame implements ActionListener {
	    Label l1, l2, l3;
	    TextField t1, t2, t3;
	    Button add, view, delete, clear;
	    TextArea display;

	    Connection con;
	    PreparedStatement pst;
	    ResultSet rs;

	    app() {
	        setTitle("Library Management - AWT");
	        setSize(500, 400);
	        setLayout(null);
	        setVisible(true);

	        l1 = new Label("Book ID:");
	        l2 = new Label("Book Name:");
	        l3 = new Label("Cost:");

	        t1 = new TextField();
	        t2 = new TextField();
	        t3 = new TextField();

	        add = new Button("Add");
	        view = new Button("View All");
	        delete = new Button("Delete");
	        clear = new Button("Clear");

	        display = new TextArea();

	        l1.setBounds(50, 50, 80, 20);
	        t1.setBounds(150, 50, 200, 20);

	        l2.setBounds(50, 80, 80, 20);
	        t2.setBounds(150, 80, 200, 20);

	        l3.setBounds(50, 110, 80, 20);
	        t3.setBounds(150, 110, 200, 20);

	        add.setBounds(50, 150, 80, 30);
	        view.setBounds(140, 150, 80, 30);
	        delete.setBounds(230, 150, 80, 30);
	        clear.setBounds(320, 150, 80, 30);

	        display.setBounds(50, 200, 350, 150);

	        add(l1); add(t1);
	        add(l2); add(t2);
	        add(l3); add(t3);
	        add(add); add(view); add(delete); add(clear);
	        add(display);

	        add.addActionListener(this);
	        view.addActionListener(this);
	        delete.addActionListener(this);
	        clear.addActionListener(this);

	        addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent we) {
	                System.exit(0);
	            }
	        });
	    }

	    public void actionPerformed(ActionEvent ae) {
	        String command = ae.getActionCommand();
	        int id, cost;

	        try {
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "subi@2004");

	            switch (command) {
	                case "Add":
	                    id = Integer.parseInt(t1.getText());
	                    String name = t2.getText();
	                    cost = Integer.parseInt(t3.getText());
	                    pst = con.prepareStatement("INSERT INTO boo VALUES (?, ?, ?)");
	                    pst.setInt(1, id);
	                    pst.setString(2, name);
	                    pst.setInt(3, cost);
	                    pst.executeUpdate();
	                    display.setText("Book Added Successfully!");
	                    break;

	                case "View All":
	                    pst = con.prepareStatement("SELECT * FROM boo");
	                    rs = pst.executeQuery();
	                    display.setText("Book Records:\n");
	                    while (rs.next()) {
	                        display.append("ID: " + rs.getInt(1) +
	                                       ", Name: " + rs.getString(2) +
	                                       ", Cost: " + rs.getInt(3) + "\n");
	                    }
	                    break;

	                case "Delete":
	                    id = Integer.parseInt(t1.getText());
	                    pst = con.prepareStatement("DELETE FROM boo WHERE book_id = ?");
	                    pst.setInt(1, id);
	                    int rows = pst.executeUpdate();
	                    if (rows > 0)
	                        display.setText("Book Deleted Successfully!");
	                    else
	                        display.setText("Book ID not found.");
	                    break;

	                case "Clear":
	                    t1.setText(""); t2.setText(""); t3.setText(""); display.setText("");
	                    break;
	            }

	            pst.close();
	            con.close();

	        } catch (Exception e) {
	            display.setText("Error: " + e.getMessage());
	        }
	    }

	    public static void main(String[] args) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            new app();
	        } catch (Exception e) {
	            System.out.println("Driver Load Error: " + e);
	        }
	    }
	}


