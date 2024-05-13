import services.ServiceUtilisateurImpl;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import services.ServiceUtilisateurImpl;

import javax.swing.*;
import java.sql.*;

public class adminpage extends JFrame {
    private JTextField textField1;
    private JList<String> list1;
    private JTextPane usersTextPane;
    private ServiceUtilisateurImpl serviceUtilisateur;

    public adminpage() throws SQLException {
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String url = "jdbc:mysql://localhost:3306/biblio";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "SELECT * FROM user";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Execute the query and fetch the results
        ResultSet resultSet = statement.executeQuery();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        while (resultSet.next()) {
            // Assuming the user has a 'name' column
            listModel.addElement(resultSet.getString("nom"));
        }

        // Set the model to the list
        list1 = new JList<>(listModel);

        add(list1); // Add the list to the JFrame

        setVisible(true);
    }
}