package b;

import java.sql.*;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        Connection con;
        ResultSet rs;
        PreparedStatement pst;
        int ch = 0, i;
        String nam;
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Driver Load Error: " + e);
        }

        do {
            System.out.println("\n========= Library Menu =========");
            System.out.println("1. Create Table");
            System.out.println("2. Add Book");
            System.out.println("3. Modify Book");
            System.out.println("4. Display Books");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("\nCreating Table...\n");
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "subi@2004");
                        Statement st = con.createStatement();
                        st.executeUpdate("CREATE TABLE boo(book_id INT PRIMARY KEY, book_name VARCHAR(25), cost INT)");
                        System.out.println("Table Created Successfully");
                        st.close();
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error during table creation: " + e);
                    }
                    break;

                case 2:
                    System.out.println("\nAdd Book Record");
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    System.out.print("Book Name: ");
                    nam = sc.next();
                    System.out.print("Cost: ");
                    int cost = sc.nextInt();
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "subi@2004");
                        pst = con.prepareStatement("INSERT INTO boo (book_id, book_name, cost) VALUES (?, ?, ?)");
                        pst.setInt(1, id);
                        pst.setString(2, nam);
                        pst.setInt(3, cost);
                        i = pst.executeUpdate();
                        System.out.println("Record Added Successfully");
                        pst.close();
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Insertion Error: " + e);
                    }
                    break;

                case 3:
                    System.out.println("\nModify Book Record");
                    System.out.print("Book ID to Update: ");
                    id = sc.nextInt();
                    System.out.print("New Book Name: ");
                    nam = sc.next();
                    System.out.print("New Cost: ");
                    cost = sc.nextInt();
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "subi@2004");
                        pst = con.prepareStatement("UPDATE boo SET book_name = ?, cost = ? WHERE book_id = ?");
                        pst.setString(1, nam);
                        pst.setInt(2, cost);
                        pst.setInt(3, id);
                        i = pst.executeUpdate();
                        if (i > 0)
                            System.out.println("Record Updated Successfully");
                        else
                            System.out.println("Book ID not found");
                        pst.close();
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Update Error: " + e);
                    }
                    break;

                case 4:
                    System.out.println("\nDisplay All Books\n");
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "subi@2004");
                        pst = con.prepareStatement("SELECT * FROM boo");
                        rs = pst.executeQuery();
                        int j = 1;
                        while (rs.next()) {
                            System.out.println("Record " + j++);
                            System.out.println("Book ID: " + rs.getInt("book_id"));
                            System.out.println("Book Name: " + rs.getString("book_name"));
                            System.out.println("Cost: " + rs.getInt("cost"));
                            System.out.println("---------------------------");
                        }
                        pst.close();
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Display Error: " + e);
                    }
                    break;

                case 5:
                    System.out.println("\nDelete Book Record");
                    System.out.print("Enter Book ID to Delete: ");
                    id = sc.nextInt();
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "subi@2004");
                        pst = con.prepareStatement("DELETE FROM boo WHERE book_id = ?");
                        pst.setInt(1, id);
                        i = pst.executeUpdate();
                        if (i > 0)
                            System.out.println("Record Deleted Successfully");
                        else
                            System.out.println("Book ID not found");
                        pst.close();
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Deletion Error: " + e);
                    }
                    break;

                case 6:
                    System.out.println("Application Closed.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (ch != 6);

        sc.close();
    }
}
