//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb"; // testdb is the DB name
        String user = "root"; // default user
        String password = ""; // default password is empty in XAMPP

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            // Create Statement
            Statement stmt = conn.createStatement();

            // Execute Query
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");

            // Read Results
//            while (rs.next()) {
//                System.out.println("Data: " + rs.getString("your_column_name"));
//            }

            // Print Result Set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double cgpa = rs.getDouble("cgpa");
                String address = rs.getString("address");

                System.out.println("ID: " + id + ", Name: " + name + ", CGPA: " + cgpa + ", Address: " + address);
            }

            // Close Connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
