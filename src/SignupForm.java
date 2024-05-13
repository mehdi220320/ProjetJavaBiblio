import entities.User;
import services.ServiceUtilisateurImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


    public class SignupForm extends JDialog {
        private JTextField tfUsername;
        private JTextField tfPassword;
        private JTextField tfEmail;
        private JButton btnCancel;
        private JButton btnRegister;
        private JPanel SignupPanel;
        private JTextField tfConfirmePass;
        private JTextField ftlastName;

        private ServiceUtilisateurImpl serviceUtilisateur;

        public SignupForm(JFrame parent, ServiceUtilisateurImpl serviceUtilisateur) {
            super(parent);
            setTitle("Create a new account");
            setContentPane(SignupPanel);
            setMinimumSize(new Dimension(600, 600));
            setModal(true);
            setLocationRelativeTo(parent);

            this.serviceUtilisateur = serviceUtilisateur;

            btnRegister.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registerUser();
                }
            });
            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    LoginForm loginForm = new LoginForm(null);
                }
            });
            setVisible(true);

        }

        private void registerUser() {
            String firstname = tfUsername.getText();
            String lastName = ftlastName.getText();
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            String confirmepass = tfConfirmePass.getText();
            if (firstname.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmepass.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter all fields",
                        "Try Again",
                        JOptionPane.ERROR_MESSAGE);
                System.out.println("Register failed");
                return;
            } else if (!password.equals(confirmepass)) {
                JOptionPane.showMessageDialog(this,
                        "Please Confirm your password",
                        "Try Again",
                        JOptionPane.ERROR_MESSAGE);
                System.out.println("Register failed");
                return;
            } else {
                User newUser = new User(firstname, lastName, email, password);
                if (this.serviceUtilisateur.addUtilisateur(newUser)) {
                    JOptionPane.showMessageDialog(this,
                            "Congratulations !!",
                            "Account created",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    System.out.println("Register succeed");
                    this.serviceUtilisateur.AfficheUsers();
                    dispose();
                    LoginForm loginForm = new LoginForm(null);

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Account already Exists",
                            "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println("Register failed");
                    return;

                }
            }
        }

//        public static void main(String[] args) {
//            ServiceUtilisateurImpl serviceUtilisateur = new ServiceUtilisateurImpl();
//            SignupForm signupForm = new SignupForm(null, serviceUtilisateur);
//        }
    }