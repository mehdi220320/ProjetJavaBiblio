package services;

import entities.User;
import entities.demande;

import java.sql.*;

public class demandeimpl {

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

    public boolean addDemande(demande Demande) {
        try {
            String sql = "INSERT INTO demande (titre,auteur,user_id) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Demande.getTitre());
            statement.setString(2, Demande.getAuteur());
            statement.setInt(3, Demande.getUser_id());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new demande was inserted successfully!");

                emailsender EmailSender = new emailsender();
                User user=new ServiceUtilisateurImpl().getById(Demande.getUser_id());
                if (user != null && user.getEmail() != null) {
                    String recipientEmail = user.getEmail();
                    String subject = "Votre demande a été bien enregistrée";
                    String body = "Cher " + user.getNom() + ",\n\nVotre demande pour le livre "+Demande.getTitre()+"a été bien enregistrée";
                    EmailSender.sendEmail(recipientEmail, subject, body);
                } else {
                    System.out.println("User not found with id: " + Demande.getId() + " or user email is null");
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet getDemande() {
        try {
            String sql = "SELECT * FROM demande";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getDemandeByUser(int user_id) {
        try {
            String sql = "SELECT * FROM demande WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            ResultSet result = statement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

public void deleteDemande(int id) {
        String sql = "DELETE FROM demande WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A demande was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updatenombrepages(int idoc, int nbp, int user_id) {
        String sql = "UPDATE demande SET nbpage = ? WHERE id = ? AND user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, nbp);
            statement.setInt(2, idoc);
            statement.setInt(3, user_id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing demande was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }}






public static void main(String[] args) {
        demandeimpl demandeimpl = new demandeimpl();
        demande Demande = new demande("titre", "aurteur", 35);
        demandeimpl.addDemande(Demande);
        demandeimpl.getDemande();
        demandeimpl.getDemandeByUser(35);
        demandeimpl.deleteDemande(1);
    }
}
