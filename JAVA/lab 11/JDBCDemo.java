import java.sql.*;

public class JDBCDemo {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String password = "6532";

        try {
            // 1. Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded successfully!");

            // 2. Establish Connection
            try (Connection con =
                    DriverManager.getConnection(url, user, password)) {

                System.out.println("Database connected successfully!");

                // 3. CREATE - Insert a new student
                String insertSQL =
                        "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";

                try (PreparedStatement ps =
                        con.prepareStatement(insertSQL)) {

                    ps.setInt(1, 106);
                    ps.setString(2, "Gaurav");
                    ps.setInt(3, 20);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        System.out.println(
                                "CREATE: Record inserted successfully!");
                    }
                }

                // 4. READ - Display all students
                System.out.println("\nREAD: Students Records");

                String selectSQL = "SELECT * FROM students";

                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery(selectSQL)) {

                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("id") + " - " +
                                rs.getString("name") + " - " +
                                rs.getInt("age")
                        );
                    }
                }

                // 5. UPDATE - Change a student's age
                String updateSQL =
                        "UPDATE students SET age = ? WHERE id = ?";

                try (PreparedStatement ps =
                        con.prepareStatement(updateSQL)) {

                    ps.setInt(1, 21);
                    ps.setInt(2, 101);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        System.out.println(
                                "\nUPDATE: Student age changed successfully!"
                        );
                    } else {
                        System.out.println(
                                "\nUPDATE: No matching student found."
                        );
                    }
                }

                // 6. DELETE - Delete the student
                String deleteSQL =
                        "DELETE FROM students WHERE id = ?";

                try (PreparedStatement ps =
                        con.prepareStatement(deleteSQL)) {

                    ps.setInt(1, 104);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        System.out.println(
                                "DELETE: Student record deleted successfully!"
                        );
                    } else {
                        System.out.println(
                                "DELETE: No matching student found."
                        );
                    }
                }

                System.out.println(
                        "\nAll CRUD operations completed."
                );

            } // Connection closes automatically here

        } catch (ClassNotFoundException e) {
            System.out.println(
                    "ERROR: MySQL JDBC Driver not found!");

            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println(
                    "ERROR: Database operation failed!");
            System.out.println("Reason: " + e.getMessage());
            System.out.println(
                    "SQL State: " + e.getSQLState());
            System.out.println(
                    "Error Code: " + e.getErrorCode());

        } catch (Exception e) {
            System.out.println(
                    "ERROR: An unexpected exception occurred!");
            e.printStackTrace();
        }
    }
}
