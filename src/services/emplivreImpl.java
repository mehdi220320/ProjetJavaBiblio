package services;

import entities.emplivre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class emplivreImpl {

  emplivre emprunt;


    String url = "jdbc:mysql://localhost:3306/biblio";
    String username = "root";
    String password = "";
    private Connection connection;

    {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   void addEmprunt(emplivre emprunt) {
       try {
           String sql = "INSERT INTO takebook (id_livre,user_id,date_emp,date_expiration,page_arr) VALUES (?,?,?, ?,?)";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, emprunt.getId_livre());
           statement.setInt(2, emprunt.getId_user());
           statement.setObject(3, emprunt.getDate_emp());
           statement.setObject(4, emprunt.getDate_expiration());
           statement.setInt(5, emprunt.getPage_arr());
           int rowsInserted = statement.executeUpdate();
           if (rowsInserted > 0) {
               System.out.println("A new emprunt was inserted successfully!");
           }
       } catch (SQLException e) {
           System.out.println("Connection Failed! Check output console");
           e.printStackTrace();

       }


   }

public static void main(String[] args) {
    emplivreImpl emplivre = new emplivreImpl();
    emplivre emprunt = new emplivre(1, 1, java.time.LocalDateTime.now(), java.time.LocalDateTime.now());
    emplivre.addEmprunt(emprunt);
}

}
