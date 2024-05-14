package services;


import entities.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDocumentImpl  {

    String url = "jdbc:mysql://localhost:3306/biblio";
    String username = "root";
    String password = "";
    private Connection connection;
    Livre livre ;
    {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addDocument(Livre livre) {
        try {
            String sql = "INSERT INTO livre (titre,auteur,nbpage) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, livre.getTitre());
            statement.setString(2, livre.getAuteur());
            statement.setInt(3, livre.getNbpage());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new document was inserted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();


        }
        return false;
    }

public ResultSet getDocuments() {
    List<Livre> emprunts = new ArrayList<>();
    String sql = "SELECT * FROM livre";
    try {
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;

    } catch (SQLException e) {
        System.out.println("Connection Failed! Check output console");
        e.printStackTrace();
    }
    return null;
}
public ResultSet getlivre(int id) {
    String sql = "SELECT * FROM livre WHERE id_livre = ?";
    try {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    } catch (SQLException e) {
        System.out.println("Connection Failed! Check output console");
        e.printStackTrace();
    }
    return null;


}

public void deleteDocument(int id) {
    String sql = "DELETE FROM livre WHERE id_livre = ?";
    try {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A document was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Connection Failed! Check output console");
        e.printStackTrace();
    }}
public Livre getById(int id) {
    Livre livre = null;
    try {

        String sql = "SELECT * FROM livre WHERE id_livre = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            livre = new Livre();
            livre.setId_livre(resultSet.getInt("id_livre"));
            livre.setTitre(resultSet.getString("titre"));
            livre.setAuteur(resultSet.getString("auteur"));
            livre.setNbpage(resultSet.getInt("nbpage"));
        }
    } catch (SQLException e) {
        System.out.println("Connection Failed! Check output console");
        e.printStackTrace();
    }
    return livre;
}



    }
