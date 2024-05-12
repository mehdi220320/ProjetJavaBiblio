import java.sql.*;

public class jdbcdemo {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/biblio";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }





}
