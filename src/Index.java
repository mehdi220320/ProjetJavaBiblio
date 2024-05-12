import services.ServiceUtilisateurImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JDialog {
    private JButton registerButton;
    private JButton loginButton;
    private JPanel IndexPanel;

    public Index(JFrame parent) {
        super(parent);
        setTitle("Login Account");
        setContentPane(IndexPanel);
        setMinimumSize(new Dimension(600,600));
        setModal(true);
        setLocationRelativeTo(parent);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignupForm signupForm = new SignupForm(parent, new ServiceUtilisateurImpl());
                signupForm.setVisible(true);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm form=new LoginForm(parent);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        Index index = new Index(null);
    }
}