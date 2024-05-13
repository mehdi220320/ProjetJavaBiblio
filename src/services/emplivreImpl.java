package services;

import entities.emplivre;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public void deleteEmprunt(int id) {
        String sql = "DELETE FROM takebook WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A emprunt was deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    public void updateEmprunt(emplivre emprunt) {
        String sql = "UPDATE takebook SET page_arr = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, emprunt.getPage_arr());
            statement.setInt(2, emprunt.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing emprunt was updated successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    public emplivre getEmprunt(int id) {
        String sql = "SELECT * FROM takebook WHERE id = ?";
        emplivre emprunt = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                emprunt = new emplivre();
                emprunt.setId(resultSet.getInt("id"));
                emprunt.setId_livre(resultSet.getInt("id_livre"));
                emprunt.setId_user(resultSet.getInt("user_id"));
                emprunt.setDate_emp(resultSet.getObject("date_emp", LocalDateTime.class));
                emprunt.setDate_expiration(resultSet.getObject("date_expiration", LocalDateTime.class));
                emprunt.setPage_arr(resultSet.getInt("page_arr"));
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return emprunt;
    }

    public List<emplivre> getAllEmprunts() {
        List<emplivre> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM takebook";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                emplivre emprunt = new emplivre();
                emprunt.setId(resultSet.getInt("id"));
                emprunt.setId_livre(resultSet.getInt("id_livre"));
                emprunt.setId_user(resultSet.getInt("user_id"));
                emprunt.setDate_emp(resultSet.getObject("date_emp", LocalDateTime.class));
                emprunt.setDate_expiration(resultSet.getObject("date_expiration", LocalDateTime.class));
                emprunt.setPage_arr(resultSet.getInt("page_arr"));
                emprunts.add(emprunt);
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return emprunts;
    }
    public List<emplivre> getEmpruntsByUser(int id_user) {
        List<emplivre> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM takebook WHERE user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_user);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                emplivre emprunt = new emplivre();
                emprunt.setId(resultSet.getInt("id"));
                emprunt.setId_livre(resultSet.getInt("id_livre"));
                emprunt.setId_user(resultSet.getInt("user_id"));
                emprunt.setDate_emp(resultSet.getObject("date_emp", LocalDateTime.class));
                emprunt.setDate_expiration(resultSet.getObject("date_expiration", LocalDateTime.class));
                emprunt.setPage_arr(resultSet.getInt("page_arr"));
                emprunts.add(emprunt);
            }
            for (emplivre emprunt : emprunts) {
                System.out.println(emprunt.getId_user());
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return emprunts;
    }
    public int getNumberOfEmpruntsByUser(int id_user) {
        List<emplivre> emprunts = getEmpruntsByUser(id_user);
        return emprunts.size();
    }
    public void updatenombrepages(int id_livre , int page_arr,int id_user){
        String sql = "UPDATE takebook SET page_arr = ? WHERE id_livre = ? and user_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, page_arr);
            statement.setInt(2, id_livre);
            statement.setInt(3, id_user);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing emprunt was updated successfully!");

                // Fetch the number of pages of the book
                String sqlBook = "SELECT nbpage FROM livre WHERE id_livre = ?";
                PreparedStatement statementBook = connection.prepareStatement(sqlBook);
                statementBook.setInt(1, id_livre);
                ResultSet resultSet = statementBook.executeQuery();
                if (resultSet.next()) {
                    int nbp = resultSet.getInt("nbpage");

                    if (nbp == page_arr) {
                        // Update the nblivre field for the user
                        String sqlUser = "UPDATE user SET nblivre = nblivre + 1 WHERE id = ?";
                        PreparedStatement statementUser = connection.prepareStatement(sqlUser);
                        statementUser.setInt(1, id_user);
                        int rowsUpdatedUser = statementUser.executeUpdate();
                        if (rowsUpdatedUser > 0) {
                            System.out.println("The nblivre field for the user was updated successfully!");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        emplivreImpl emplivre = new emplivreImpl();
       emplivre emprunt = new emplivre(1, 1);
        emplivre emprunt2 = new emplivre(1, 2);
        emplivre emprunt3 = new emplivre(1, 2);

        emplivre.addEmprunt(emprunt);
        emplivre.addEmprunt(emprunt2);

        emplivre.getAllEmprunts();
        emplivre.deleteEmprunt(1);
        emplivre.getAllEmprunts();
        emplivre.updateEmprunt(emprunt3);
        emplivre.getEmpruntsByUser(2);
        emplivre.getEmprunt(2);
        emplivre.updatenombrepages(5, 320, 2);

    emplivre.getNumberOfEmpruntsByUser(1);
    }

}
