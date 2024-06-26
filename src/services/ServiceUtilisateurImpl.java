package services;

import entities.User;
import services.ServiceUtilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUtilisateurImpl implements ServiceUtilisateur {
    private static int user_connected;

    public static int getUser_connected() {
        return user_connected;
    }

    public static void setUser_connected(int user_connected) {
        ServiceUtilisateurImpl.user_connected = user_connected;
    }

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



    @Override
    public boolean addUtilisateur(User user) {
            try {
                String sql = "INSERT INTO user (nom,prenom,email, password,role,nblivre) VALUES (?,?,?, ?,?,? )";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, user.getNom());
                statement.setString(2, user.getPrenom());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getRole());
                statement.setInt(6, user.getNblivre());
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");

                   emailsender EmailSender = new emailsender();
                   String recipientEmail = user.getEmail();
                   String subject = "Welcome to our platform!";
                    String body = "Dear " + user.getNom() + ",\n\nWelcome to our platform! We're glad to have you with us.";
                   EmailSender.sendEmail(recipientEmail, subject, body);

                   return true;
                }
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
            }

        System.out.println("Account already Exist");
        return false;
    }

    @Override
    public void deleteUtilisateur(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User was deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> listeUser = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setEmail(resultSet.getString("email"));
                user.setNblivre(resultSet.getInt("nblivre"));

                listeUser.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return listeUser;
    }

    public User getById(int id) {

        User user = new User();

        try {

            String sql = "SELECT * FROM user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setNblivre(Integer.parseInt(resultSet.getString("nblivre")));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public void AfficheUsers() {

    }

    public ResultSet getUsers2() {
        List<User> listeUser = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT \n" +
                    "    user.id,\n" +
                    "    user.nom,\n" +
                    "    user.prenom,\n" +
                    "    user.email,\n" +
                    "    user.nblivre,\n" +
                    "    (SELECT COUNT(*) FROM takebook WHERE user_id = user.id) AS Numlivemp \n" +
                    "FROM \n" +
                    "    user;");

            return resultSet;
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public User getByEmail(String email) {
        User user = null;
        try {

            String sql = "SELECT * FROM user WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setNblivre(Integer.parseInt(resultSet.getString("nblivre")));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User login(String email, String password2) {
        User user = null;
        try {

            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password2);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return user;
    }


}