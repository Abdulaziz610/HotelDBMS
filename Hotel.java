import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Hotel {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=HotelDBMS;" + "encrypt=true;" + "trustServerCertificate=true";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "root";
    private static final String INSERT_HOTEL_SQL = "INSERT INTO Hotels (hotel_name, hotel_location, created_date, updated_date, is_Active) VALUES (?, ?, ?, ?, ?)";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HOTEL_SQL)) {
            Random random = new Random();
            for (int i = 1; i <= 10000; i++) {
                preparedStatement.setString(1, "Hotel " + i);
                preparedStatement.setString(2, "Location " + random.nextInt(1000));
                preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
                preparedStatement.setDate(4, null);
                preparedStatement.setBoolean(5, true);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("Hotels inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting hotels: " + e.getMessage());
        }
    }
}