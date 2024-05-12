import services.ServiceUtilisateurImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm  extends  JDialog{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton registerButton;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel LoginPanel;
    ServiceUtilisateurImpl serviceUtilisateur ;
    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login Account");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(600,600));
        setModal(true);
        setLocationRelativeTo(parent);
        this.serviceUtilisateur=new ServiceUtilisateurImpl() ;
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignupForm signupForm = new SignupForm(null);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Index index=new Index(null);
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setVisible(true);
    }

}
