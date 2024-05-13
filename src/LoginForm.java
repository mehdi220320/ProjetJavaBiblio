import entities.User;
import services.ServiceUtilisateurImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;


public class LoginForm extends JDialog {
    private JTextField ftEmail;
    private JPasswordField ftPassword;
    private JButton registerButton;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel LoginPanel;
    private ServiceUtilisateurImpl serviceUtilisateur;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login Account");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        this.serviceUtilisateur = new ServiceUtilisateurImpl();

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignupForm signupForm = new SignupForm((JFrame) SwingUtilities.getWindowAncestor(LoginForm.this), serviceUtilisateur);
                signupForm.setVisible(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Index index = new Index(null);
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setVisible(true);
    }

    private void login() throws SQLException {
        String email = ftEmail.getText();
        String password = new String(ftPassword.getPassword());
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Login failed");
        } else {
            User user = this.serviceUtilisateur.login(email, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(this,
                            "You are welcome",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Login Successed");
                    dispose();
                    if ("admin".equals(user.getRole())) {
                        dispose();
                        Adminpage adminForm = new Adminpage(null);
                    } else {
                        System.out.println(user.getId());
                        this.serviceUtilisateur.setUser_connected(user.getId());
                        dispose();
                        new Userpage(null);
                    }
                } else {

                    JOptionPane.showMessageDialog(this,
                            "Invalid email or password",
                            "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println("Login failed");
                }
            }
        }

//    public static void main(String[] args) {
//        Index index = new Index(null);
//        index.setVisible(true);
//    }

}



