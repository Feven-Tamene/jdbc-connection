import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class JDBCDemo {

    public static void main(String[] args) {

        try {

            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String url = "jdbc:mysql://localhost:3306/StudentsDB";
            String username = "root";
            String password = "new121314!";

            Connection conn =
                    DriverManager.getConnection(url, username, password);

            System.out.println("Connected to MySQL!");

            //  Create Statement
            Statement stmt = conn.createStatement();

            String createTable =
                    "CREATE TABLE IF NOT EXISTS students2 (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(100), " +
                            "age INT, " +
                            "department VARCHAR(100))";

            stmt.executeUpdate(createTable);

            System.out.println("Table created successfully.");

            String insert =
                    "INSERT INTO students2 (name, age, department) " +
                            "VALUES ('Alemu', 22, 'Software Engineering')";

            int inserted = stmt.executeUpdate(insert);

            System.out.println(inserted + " row inserted.");

            // 6. UPDATE
            String update =
                    "UPDATE students2 " +
                            "SET age = 23 " +
                            "WHERE name = 'Alemu'";

            int updated = stmt.executeUpdate(update);

            String sql = "SELECT * FROM students2";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getString("department")
                );
            }

            rs.close();

            System.out.println(updated + " row updated.");


            String delete =
                    "DELETE FROM students2 " +
                            "WHERE name = 'Alemu'";

            int deleted = stmt.executeUpdate(delete);

            System.out.println(deleted + " row deleted.");


            stmt.close();
            conn.close();

            System.out.println("Connection closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
