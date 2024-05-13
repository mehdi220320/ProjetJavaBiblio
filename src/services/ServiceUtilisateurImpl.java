package services;

import entities.User;
import services.ServiceUtilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUtilisateurImpl implements ServiceUtilisateur {
    List<User> listeUser = new ArrayList<>();

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

    public boolean UserExist(User user) {
        for (User us : listeUser) {
            if (us.equals(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addUtilisateur(User user) {
        if (!UserExist(user)) {
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

                    // Send a welcome email
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
                System.out.println("A user was deleted successfully!");
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

    public ResultSet getUsers2() {
        List<User> listeUser = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            return resultSet;
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void AfficheUsers() {
        for (User us : listeUser) {
            System.out.println(us.toString());
        }
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
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role")); // Assuming you have a 'role' column in your 'user' table
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
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role")); // Assuming you have a 'role' column in your 'user' table
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        ServiceUtilisateurImpl serviceUtilisateur = new ServiceUtilisateurImpl();
        User user = new User("John", "Doe", "jaouher2002@gmail.com", "password", "user");
    serviceUtilisateur.addUtilisateur(user);
    }
}