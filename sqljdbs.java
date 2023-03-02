import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class sqljdbs {

    public static void main(String[] args) {

        String url ="jdbc:sqlserver://localhost:1433;" +
                "databaseName=HotelDBMS;" +
                "encrypt=true;" +
                "trustServerCertificate=true";
        String username = "sa";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement()) {

            // Create Hotels table
            String hotelsTable = "CREATE TABLE Hotels "
                    + "(id INT PRIMARY KEY, "
                    + "hotel_name VARCHAR(255) NOT NULL, "
                    + "hotel_location VARCHAR(255), "
                    + "created_date DATE NOT NULL, "
                    + "updated_date DATE, "
                    + "is_Active bit NOT NULL)";

            statement.executeUpdate(hotelsTable);

            // Create Room_Type table
            String roomTypeTable = "CREATE TABLE Room_Type "
                    + "(id INT PRIMARY KEY, "
                    + "room_type_name VARCHAR(255) NOT NULL, "
                    + "created_date DATE, "
                    + "updated_date DATE, "
                    + "is_Active bit NOT NULL)";

            statement.executeUpdate(roomTypeTable);

            // Create Rooms table
            String roomsTable = "CREATE TABLE Rooms "
                    + "(id INT PRIMARY KEY, "
                    + "room_type_id INT, "
                    + "hotel_id INT, "
                    + "created_date DATE NOT NULL, "
                    + "updated_date DATE, "
                    + "is_Active bit NOT NULL, "
                    + "FOREIGN KEY (room_type_id) REFERENCES Room_Type(id), "
                    + "FOREIGN KEY (hotel_id) REFERENCES Hotels(id))";

            statement.executeUpdate(roomsTable);

            // Create Guests table
            String guestsTable = "CREATE TABLE Guests "
                    + "(id INT PRIMARY KEY, "
                    + "guest_name VARCHAR(255) NOT NULL, "
                    + "guest_phone VARCHAR(255) NOT NULL, "
                    + "guest_accompanying_members INT NOT NULL, "
                    + "guest_payment_amount INT NOT NULL, "
                    + "room_id INT, "
                    + "hotel_id INT, "
                    + "created_date DATE NOT NULL, "
                    + "updated_date DATE, "
                    + "is_Active bit NOT NULL, "
                    + "FOREIGN KEY (room_id) REFERENCES Rooms(id), "
                    + "FOREIGN KEY (hotel_id) REFERENCES Hotels(id))";

            statement.executeUpdate(guestsTable);

            // Create Employee_Type table
            String employeeTypeTable = "CREATE TABLE Employee_Type "
                    + "(id INT PRIMARY KEY, "
                    + "employee_type_name VARCHAR(255) NOT NULL, "
                    + "created_date DATE NOT NULL, "
                    + "updated_date DATE, "
                    + "is_Active bit NOT NULL)";

            statement.executeUpdate(employeeTypeTable);

            // Create Employees table
            String employeesTable = "CREATE TABLE Employees "
                    + "(id INT PRIMARY KEY, "
                    + "employee_type_id INT, "
                    + "room_id INT, "
                    + "created_date DATE NOT NULL, "
                    + "updated_date DATE, "
                    + "is_Active bit NOT NULL, "
                    + "FOREIGN KEY (employee_type_id) REFERENCES Employee_Type(id), "
                    + "FOREIGN KEY (room_id) REFERENCES Rooms(id))";

            statement.executeUpdate(employeesTable);

            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }
}